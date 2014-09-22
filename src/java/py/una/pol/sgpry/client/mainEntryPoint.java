/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.sgpry.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import py.una.pol.sgpry.client.views.Login;

/**
 * Main entry point.
 *
 * @author Ricardo Chirife <chirife.ricardo@gmail.com>
 */
public class mainEntryPoint extends Viewport implements EntryPoint {
    @Override
    public void onModuleLoad() {
        Login login = new Login();
        CenterLayoutContainer clc = new CenterLayoutContainer();
        clc.add(login);
        RootPanel.get("loading").setVisible(false);
        this.add(clc);
        RootPanel.get().add(this);
    }
}
