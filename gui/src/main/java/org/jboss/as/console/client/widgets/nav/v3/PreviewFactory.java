package org.jboss.as.console.client.widgets.nav.v3;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Heiko Braun
 * @since 13/02/15
 */
public interface PreviewFactory<T> {

    void createPreview(T data, AsyncCallback<SafeHtml> callback);

    static void NO_SELECTION_PREVIEW(AsyncCallback<SafeHtml> callback) {
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
        builder.appendHtmlConstant("<div class='preview-content'><h2>Nothing is selected</h2></div>");
        callback.onSuccess(builder.toSafeHtml());
    }
}
