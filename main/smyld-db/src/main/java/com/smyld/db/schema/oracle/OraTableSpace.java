package com.smyld.db.schema.oracle;

import com.smyld.db.schema.TableSpace;

public class OraTableSpace extends TableSpace {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int initialExtent;
	int nextExtent;
	int minExtent;
	int maxExtent;
	int pctIncrease;
	boolean logging;
	String spaceManagement;

	public OraTableSpace() {
	}

	public int getInitialExtent() {
		return initialExtent;
	}

	public void setInitialExtent(int initialExtent) {
		this.initialExtent = initialExtent;
	}

	public int getNextExtent() {
		return nextExtent;
	}

	public void setNextExtent(int nextExtent) {
		this.nextExtent = nextExtent;
	}

	public int getMinExtent() {
		return minExtent;
	}

	public void setMinExtent(int minExtent) {
		this.minExtent = minExtent;
	}

	public int getMaxExtent() {
		return maxExtent;
	}

	public void setMaxExtent(int maxExtent) {
		this.maxExtent = maxExtent;
	}

	public int getPctIncrease() {
		return pctIncrease;
	}

	public void setPctIncrease(int pctIncrease) {
		this.pctIncrease = pctIncrease;
	}

	public boolean isLogging() {
		return logging;
	}

	public void setLogging(boolean logging) {
		this.logging = logging;
	}

	public String getSpaceManagement() {
		return spaceManagement;
	}

	public void setSpaceManagement(String spaceManagement) {
		this.spaceManagement = spaceManagement;
	}

	@Override
	public boolean equals(Object compareSpace) {
		if (compareSpace instanceof OraTableSpace) {
			OraTableSpace comp = (OraTableSpace) compareSpace;
			if (comp.getName().equals(getName()))
				if (comp.getInitialExtent() == getInitialExtent())
					if (comp.getNextExtent() == getNextExtent())
						if (comp.getMinExtent() == getMinExtent())
							if (comp.getMaxExtent() == getMaxExtent())
								if (comp.getSpaceManagement().equals(
										getSpaceManagement()))
									return (comp.isLogging() == isLogging());
		}
		return false;
	}
}
