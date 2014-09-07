package com.ui4j.api.dialog;

import com.ui4j.api.util.Logger;
import com.ui4j.api.util.LoggerFactory;

class AcceptingConfirmDialogHandler implements ConfirmHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AcceptingConfirmDialogHandler.class);

    @Override
    public boolean handle(DialogEvent event) {
        LOG.info("Replying [true] to message: " + event.getMessage());
        return true;
    }
}
