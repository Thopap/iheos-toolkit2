package gov.nist.toolkit.desktop.client.content;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import gov.nist.toolkit.desktop.client.event.StatusEvent;
import gov.nist.toolkit.desktop.client.event.TkEventBus;
import gov.nist.toolkit.desktop.client.injection.TkGInjector;
import gov.nist.toolkit.desktop.client.widget.ContentPanel;
import gov.nist.toolkit.desktop.client.widget.Status;
import gov.nist.toolkit.desktop.client.widget.ToolPanel;

import java.util.logging.Logger;

/**
 *
 */
public class TkShell extends ResizeComposite implements StatusEvent.StatusHandler {
    private static final TkGInjector INJECTOR = TkGInjector.INSTANCE;
    private final TkEventBus eventBus = INJECTOR.getEventBus();

    private static final Logger log = Logger.getLogger(TkShell.class.getName());

    interface TkShellUiBinder extends UiBinder<Widget, TkShell> {
    }

    private static TkShellUiBinder binder = GWT.create(TkShellUiBinder.class);

    @UiField
    ContentPanel contentPanel;

    @UiField
    Status status;

    @UiField
    Label logoLabel;

    public TkShell() {
        initWidget(binder.createAndBindUi(this));
        logoLabel.setText("XDS Toolkit");
        contentPanel.addTab("Home", new DateBox());
//        if (!GWT.isProdMode()) {
            contentPanel.addTab("Log", new LogTab());
//        }

        ToolPanel tool = new ToolPanel();
        tool.addMain(new Label("main"));
        tool.addContext(new Label("Environment"));
        tool.addButton("Reset");
        contentPanel.addTab("Tool", tool);

        TkEventBus.get().addHandler(StatusEvent.TYPE, this);
        TkEventBus.get().fireEvent(new StatusEvent("your message"));
        log.info("Module loaded. BaseURL - " + GWT.getModuleBaseURL());
    }

    @Override
    public void onStatusChange(StatusEvent event) {
        status.setText(event.getStatus());
    }
}