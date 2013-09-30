package jackal.gui.threads;

import jackal.gui.UICallback;

/**
 * User: jackal
 * Date: 30.09.13
 * Time: 14:55
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public abstract class CommonImpotThreadImpl implements Runnable, LongOperations {

    protected UICallback ui;
    protected boolean cancaled = false;
    protected boolean executed = false;

    public CommonImpotThreadImpl(UICallback ui) {
        this.ui = ui;
    }

    protected synchronized boolean isCanceled() {
        return cancaled;
    }

    @Override
    public synchronized void cancel() {
        cancaled = true;
    }

    @Override
    public synchronized void execute() {
        if (executed) {
            throw new IllegalStateException("Thread already executed");
        } else {
            executed = true;
            ui.disableButtons();
            Thread t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {}
}
