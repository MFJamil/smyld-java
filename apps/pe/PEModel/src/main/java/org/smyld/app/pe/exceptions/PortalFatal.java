package org.smyld.app.pe.exceptions;

import org.smyld.SMYLDException;

public class PortalFatal extends SMYLDException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PortalFatal() {
    }

    public PortalFatal(String message) {
        super(message);
    }

}
