/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.sgpry.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import py.una.pol.sgpry.client.services.UsuariosService;
import py.una.pol.sgpry.client.services.UsuariosServiceAsync;
import py.una.pol.sgpry.server.db.responses.ResponseLogin;

/**
 *
 * @author Ricardo Chirife <chirife.ricardo@gmail.com>
 */
public class Login extends FramedPanel {

    private final FormPanel form;
    private final TextField user;
    private final PasswordField pswd;
    private final TextButton login;
    private UsuariosServiceAsync service = GWT.create(UsuariosService.class);
    private Login _this;

    public Login() {
        _this = this;
        this.setWidth(375);
        this.setHeight("auto");
        this.setHeadingText("Login");
        this.setButtonAlign(BoxLayoutPack.CENTER);
        this.setLayoutData(new CenterLayoutContainer());

        form = new FormPanel();
        form.setWidth("auto");
        form.setHeight("auto");
        form.setLabelAlign(FormPanel.LabelAlign.LEFT);

        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
        form.add(vlc, new MarginData(10));

        user = new TextField();
        user.setAllowBlank(false);
        vlc.add(new FieldLabel(user, "Usuario"), new VerticalLayoutData(-18, -1));

        pswd = new PasswordField();
        pswd.setAllowBlank(false);
        vlc.add(new FieldLabel(pswd, "Contraseña"), new VerticalLayoutData(-18, -1));

        pswd.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    if (form.isValid()) {
                        RootPanel.get("loading").setVisible(true);
                        _this.login.setEnabled(false);
                        login();
                    }
                }
            }
        });

        login = new TextButton("Ingresar");
        login.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                if (_this.form.isValid()) {
                    RootPanel.get("loading").setVisible(true);
                    _this.login.setEnabled(false);
                    login();
                }
            }
        });

        this.addButton(login);
        _this.add(form);
    }

    private void login() {
        service.login(_this.user.getValue(), _this.pswd.getValue(), new AsyncCallback<ResponseLogin>() {
            @Override
            public void onFailure(Throwable caught) {
                RootPanel.get("loading").setVisible(false);
                _this.login.setEnabled(true);
                AlertMessageBox amb = new AlertMessageBox("Error", caught.getMessage());
                amb.show();
            }

            @Override
            public void onSuccess(ResponseLogin result) {
                RootPanel.get("loading").setVisible(false);
                _this.login.setEnabled(true);
                Info.display("EXITO", result.getStatus().getRespuesta());
            }
        });
    }
}
