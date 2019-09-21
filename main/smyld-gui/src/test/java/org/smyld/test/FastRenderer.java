package org.smyld.test;

/** 
 * This application demonstrates how one can squeeze some extra
 * performance out of JList. A custom cell renderer is used - which
 * only displays left justified strings - and the list is configured
 * with fixed size cells.  A simple benchmark measures the performance
 * gained with this approach relative to a similarly configured 
 * JList with a default cell renderer.   Tests on Solaris show
 * about 30% improvement.
 * 
 * Tested against swing-1.1, JDK1.1.7.
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Method;

import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;


/** 
 * A CellRenderer that eliminates any of the overhead that the
 * DefaultListCellRenderer (a JLabel) adds.  Only left justified
 * strings are displayed, and cells have a fixed preferred
 * height and width.   
 */
class TextCellRenderer extends JPanel implements ListCellRenderer 
{
    String text;
    final int borderWidth = 2;
    final int baseline;
    final int width;
    final int height;

    TextCellRenderer(FontMetrics metrics, int height) {
		super();
		baseline = metrics.getAscent() + borderWidth;
		this.height = metrics.getHeight() + (2 * borderWidth);
		this.width = 200;
    }

    /** 
     * Return the renderers fixed size here.  
     */
    public Dimension getPreferredSize() {
	return new Dimension(width, height);
    }

    /**
     * Completely bypass all of the standard JComponent painting machinery.
     * This is a special case: the renderer is guaranteed to be opaque,
     * it has no children, and it's only a child of the JList while
     * it's being used to rubber stamp cells.
     * <p>
     * Clear the background and then draw the text.
     */
    public void paint(Graphics g) {
	g.setColor(getBackground());
	g.fillRect(0, 0, getWidth(), getHeight());
	g.setColor(getForeground());
	g.drawString(text, borderWidth, baseline);
    }


    /* This is is the ListCellRenderer method.  It just sets
     * the foreground and background properties and updates the
     * local text field.
     */
    public Component getListCellRendererComponent(
        JList list,
        Object value,
        int index,
        boolean isSelected,
        boolean cellHasFocus) 
    {
	if (isSelected) {
	    setBackground(list.getSelectionBackground());
	    setForeground(list.getSelectionForeground());
	}
	else {
	    setBackground(list.getBackground());
	    setForeground(list.getForeground());
	}
	text = value.toString();

	return this;
    }
}


/** 
 * Create a pair of JList components, each one displays all of the methods 
 * in JComponent.  The first one uses a custom cell renderer that trims 
 * whatever overhead it can from the DefaultListListCellRenderer, which is
 * used by the other JList.
 */
public class FastRenderer
{
    static long benchmarkList(final JList list, String msg) 
    {
	Runnable doScroll = new Runnable() {
	    public void run() {
		int index = list.getSelectedIndex();
		list.setSelectedIndex(index + 1);
		list.ensureIndexIsVisible(index + 1);
	    }
	};

	Runnable doNothing = new Runnable() {
	    public void run() {
	    }
	};

	long startTime = System.currentTimeMillis();
	
	list.setSelectedIndex(0);

	/* Each iteration of this loops queue a request to scroll
	 * the list on the event dispatching thread (we're running
	 * on the "main" thread) and then waits for all of the paint 
	 * operations caused by the scroll to finish.  Finally we 
	 * force any output that has been buffered by the AWT 
	 * implementation to be flushed (with Toolkit.sync()).  Then
	 * overhead introduced here is the same for each list so
	 * the elapsed time numbers can be safely compared.  
	 */
	for (int i = 0; i < list.getModel().getSize(); i++) {
	    try {
		SwingUtilities.invokeLater(doScroll);
		Thread.yield();
		SwingUtilities.invokeAndWait(doNothing);
		Toolkit.getDefaultToolkit().sync();
	    } 
	    catch (Exception e) {
		e.printStackTrace();
	    }
	}

	long endTime = System.currentTimeMillis();
	return endTime - startTime;
    }	 



    public static void main(String[] args) throws Exception
    {
	/* Create a ListModel for JComponent.class.getMethods() that
	 * contains the toString() version of each method object.
	 */

	ListModel model = new AbstractListModel() {
	    private String[] getMethodNames() {
		Method[] methods = JComponent.class.getMethods();
		String[] names = new String[methods.length];
		for(int i = 0; i < methods.length; i++) {
		    names[i] = methods[i].toString();
		}
		return names;
	    }
	    private final String[] names = getMethodNames();
	    public int getSize() { return names.length; }
	    public Object getElementAt(int i) { return names[i]; }
	};

	JList list1 = new JList(model);
	FontMetrics metrics = list1.getFontMetrics(list1.getFont());
	list1.setCellRenderer(new TextCellRenderer(metrics, 200));
	list1.setPrototypeCellValue(list1.getModel().getElementAt(0));
	list1.setVisibleRowCount(40);
	list1.setFixedCellWidth(400);

	/* This JList (list2) uses the default cell renderer and a model that
	 * that contains the raw method objects.  The cell renderer will
	 * convert the method objects to strings, as needed.
	 */

	JList list2 = new JList(JComponent.class.getMethods());
	list2.setPrototypeCellValue(list2.getModel().getElementAt(0));
	list2.setVisibleRowCount(40);
	list2.setFixedCellWidth(400);

	JPanel p = new JPanel(new GridLayout(1, 2, 4, 4));
	p.add(new JScrollPane(list1));
	p.add(new JScrollPane(list2));

	JFrame frame = new JFrame("Custom Renderer (left), Default Renderer (right)");

	WindowListener l = new WindowAdapter() {
	    public void windowClosing(WindowEvent e) { System.exit(0); }
	};
	frame.addWindowListener(l);

	frame.getContentPane().add(p);
	frame.pack();
	frame.setVisible(true);

	long dt1 = benchmarkList(list1, "Custom Cell Renderer");
	long dt2 = benchmarkList(list2, "Default Cell Renderer");
	int pf = (int)(((double)(dt2 - dt1) / (double)dt2) * 100.0);

	String msg = "Custom renderer is " + pf + "% faster than the default renderer";
	JOptionPane.showMessageDialog(frame, msg);
    }
}
