package org.smyld.db.schema;

import org.smyld.text.TextUtil;

public class TableColumn extends SchemaObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String type;
	int size;
	boolean nullable;

	public TableColumn() {
		setSchemaType(TAG_NAME_COLUMN);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	@Override
	public boolean equals(Object compare) {
		boolean result = false;
		if (compare instanceof TableColumn) {
			TableColumn comp = (TableColumn) compare;
			if (TextUtil.compare(name, comp.getName()))
				if (TextUtil.compare(type, comp.getType()))
					if (nullable == comp.nullable)
						return (size == comp.getSize());
		}
		return result;
	}

	public String toString1() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Column : ");
		buffer.append(name);
		buffer.append(OS_NEW_LINE);
		buffer.append("Type     : ");
		buffer.append(type);
		buffer.append(OS_NEW_LINE);
		buffer.append("Size     : ");
		buffer.append(size);
		buffer.append(OS_NEW_LINE);
		buffer.append("Nullable : ");
		buffer.append(nullable);
		buffer.append(OS_NEW_LINE);
		return buffer.toString();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Column:");
		buffer.append(name);
		buffer.append(",");
		buffer.append(type);
		buffer.append(",");
		buffer.append(size);
		buffer.append(",Nullable is ");
		buffer.append(nullable);
		return buffer.toString();
	}

	@Override
	public String printSchemaData() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(type);
		buffer.append(",");
		buffer.append(size);
		buffer.append(",Nullable is ");
		buffer.append(nullable);
		return buffer.toString();

	}

}
