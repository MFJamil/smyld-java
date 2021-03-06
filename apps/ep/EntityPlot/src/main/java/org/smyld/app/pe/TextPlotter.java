package org.smyld.app.pe;

import lombok.Getter;
import lombok.Setter;
import org.smyld.app.pe.util.Props;

import static org.smyld.app.pe.PlotConstants.ALIGNMENT_BUTTOM;
import static org.smyld.app.pe.PlotConstants.ALIGNMENT_CENTER;
import static org.smyld.app.pe.PlotConstants.ALIGNMENT_MIDDLE;
import static org.smyld.app.pe.PlotConstants.ALIGNMENT_RIGHT;
import static org.smyld.app.pe.PlotConstants.ALIGNMENT_TOP;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

@Setter
@Getter
public class TextPlotter {
	Font font = new Font("Newtimes Roman",Font.BOLD,10);
	String text;
	Color foreground = Color.BLACK;
	//Color background = Color.WHITE;
	Color background = new Color(237,232,153);
	int verticalAlignment;
	int horizontalAlignment;
	int x, y, width,singleLineHeight,height,corner=8;
	int textLeftMargin=10,textButtomMargin=10,textRightMargin=10,textTopMargin=4,textLineMargin=2,borderWidth=1;
	double totalContentHeight;
	boolean autoExpand = true,drawBorder=true,drawConnectPoint=true,drawBackground=true;
	BasicStroke borderStroke;
	BasicStroke connectStroke;

	public TextPlotter(String text) {
		this.text = text;
		init();
	}

	public TextPlotter() {
		init();
	}
	private void init(){
		borderStroke  = new BasicStroke(borderWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		connectStroke = new BasicStroke(borderWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,1.0f,new float[] {4.0f,2.0f,2.0f,2.0f},0.0f);                     // Dash phase
	}

	protected void paintContents(Graphics2D g,int connectX,int connectY) {
		if (drawBackground){
			g.setColor(getBackground());
			g.fillRoundRect(x, y, width, height, corner, corner);
		}
		
		g.setFont(font);
		g.setColor(foreground);
		if (drawBorder){
			g.setStroke(connectStroke);
			g.drawRoundRect(x, y, width, height, corner, corner);
		}
		if (drawConnectPoint)
			drawConnectionPoint(g,connectX,connectY);
		if (text != null) {
			String[] conts = text.split("\n");
			if (conts.length >= 1) {
				double singleLineHeight = font.getStringBounds(conts[0],
						g.getFontRenderContext()).getHeight();
				// Drawing contents
				float posY = detectContentsFirstLine(g, singleLineHeight,
						conts.length);
				switch (horizontalAlignment) {
				case ALIGNMENT_CENTER:
					for (String curLine : conts) {
						int txtWidth = (int) font.getStringBounds(curLine,
								g.getFontRenderContext()).getWidth();
						float posX = (float) (x + ((width - txtWidth) / 2));
						g.drawString(curLine, posX, posY);
						posY += singleLineHeight + 2;
					}

					break;
				case ALIGNMENT_RIGHT:
					for (String curLine : conts) {
						double txtWidth = font.getStringBounds(curLine,
								g.getFontRenderContext()).getWidth();
						float posX = (float) (x + width - (txtWidth + textRightMargin));
						g.drawString(curLine, posX, posY);
						posY += singleLineHeight + 2;
					}
					break;
				default:
					float posX = (float) (x + textLeftMargin + borderWidth);
					for (String curLine : conts) {
						g.drawString(curLine, posX, posY);
						posY += singleLineHeight + 2;
					}
				}
			}
		}
	}
	
	protected void drawConnectionPoint(Graphics2D g,int connectX,int connectY){
		g.setStroke(connectStroke);
		int upX=x,upY=y+height-corner*2,loX=x,loY=y+height-corner;
		if (x<connectX){upX = loX = x+width; }
		if (y>connectY){loY=y+corner*2;upY=y+corner;}
		GeneralPath gp = new GeneralPath();
		gp.moveTo(connectX, connectY);
		gp.lineTo(upX, upY);
		gp.lineTo(loX, loY);
		gp.closePath();
		g.draw(gp);
		//g.fill(gp);
	}

	protected float detectContentsFirstLine(Graphics2D g,
			double singleLineHeight, int lineNo) {
		float posY = 0;
		switch (verticalAlignment) {
		case ALIGNMENT_TOP:
			posY = (float) (y + borderWidth + textTopMargin + singleLineHeight);
			break;
		case ALIGNMENT_MIDDLE:
			posY = (float) (y + borderWidth + height / 2
					- (totalContentHeight / 2) + singleLineHeight);
			break;
		case ALIGNMENT_BUTTOM:
			posY = (float) (y + height + singleLineHeight - (totalContentHeight + textButtomMargin));
			break;

		}

		return posY;
	}

	public void calibrate(Graphics2D g) {
		if (text != null) {

			String[] conts = text.split("\n");
			if (conts.length >= 1) {
				double singleLineHeight = font.getStringBounds(conts[0],
						g.getFontRenderContext()).getHeight();
				totalContentHeight = ((singleLineHeight + textLineMargin) * conts.length)
						- textLineMargin;
				double maxTextWidth = 0;
				String longestLine = null;
				for (String curLine : conts) {
					double curWidth = font.getStringBounds(curLine,
							g.getFontRenderContext()).getWidth();
					maxTextWidth = curWidth > maxTextWidth ? curWidth
							: maxTextWidth;
				}
				double totalContentWidth = textLeftMargin + maxTextWidth
						+ (2 * borderWidth) + textRightMargin;
				int addHeight = textTopMargin + textButtomMargin
						+ (2 * borderWidth);
				if ((height) < (totalContentHeight + addHeight)) {
					if (autoExpand) {
						setHeight((int) (totalContentHeight + addHeight));
					} else {
						// Need to clip the rest
					}
				}
				if ((width) < totalContentWidth) {
					if (autoExpand) {
						setWidth((int) totalContentWidth);
					} else {
						// Need to clip the rest
					}
				}

			}
		}
	}


}
