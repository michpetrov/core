/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package org.jboss.as.console.client.shared.patching.wizard.apply;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import org.jboss.as.console.client.Console;
import org.jboss.as.console.client.shared.patching.ui.ErrorDetails;
import org.jboss.as.console.client.shared.patching.ui.PatchManagementTemplates;
import org.jboss.as.console.client.shared.patching.wizard.PatchWizard;
import org.jboss.as.console.client.shared.patching.wizard.PatchWizardStep;
import org.jboss.as.console.client.shared.patching.wizard.WizardButton;
import org.jboss.ballroom.client.widgets.common.DefaultButton;

/**
 * @author Harald Pehl
 */
public class AppliedFailedStep extends PatchWizardStep<ApplyContext, ApplyState> {

    final static PatchManagementTemplates TEMPLATES = GWT.create(PatchManagementTemplates.class);

    private ErrorDetails errorDetails;

    public AppliedFailedStep(final PatchWizard<ApplyContext, ApplyState> wizard) {
        super(wizard, Console.CONSTANTS.patch_manager_error_title(), new WizardButton(false),
                new WizardButton(Console.CONSTANTS.common_label_cancel()));
    }

    @Override
    protected IsWidget body(final ApplyContext context) {
        FlowPanel body = new FlowPanel();
        body.add(new HTML(TEMPLATES.errorPanel(Console.CONSTANTS.patch_manager_error_body())));

        errorDetails = new ErrorDetails(Console.CONSTANTS.patch_manager_show_details(),
                Console.CONSTANTS.patch_manager_hide_details());
        body.add(errorDetails);

        body.add(new HTML("<h3 class=\"patch-followup-header\">" + Console.CONSTANTS.patch_manager_possible_actions() + "</h3>"));
        HTMLPanel actions = new HTMLPanel(TEMPLATES
                .appliedFailed(Console.CONSTANTS.patch_manager_error_cancel_title(),
                        Console.CONSTANTS.patch_manager_error_cancel_body(),
                        Console.CONSTANTS.patch_manager_error_select_title(),
                        Console.CONSTANTS.patch_manager_error_select_body()));
        DefaultButton browse = new DefaultButton(Console.CONSTANTS.patch_manager_browse());
        browse.getElement().setAttribute("style", "min-width:60px;");
        browse.addStyleName("primary");
        actions.add(browse, "select-different-patch");
        body.add(actions);

        return body;
    }


    @Override
    protected void onShow(final ApplyContext context) {
        wizard.grow();
        errorDetails.setDetails(context.patchFailedDetails);
    }
}
