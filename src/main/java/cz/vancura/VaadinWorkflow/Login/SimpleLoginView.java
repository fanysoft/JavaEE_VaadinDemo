package cz.vancura.VaadinWorkflow.Login;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import cz.vancura.VaadinWorkflow.chart.ChartMainView;

public class SimpleLoginView extends CustomComponent implements View, Button.ClickListener  {

	public static final String NAME = "login";
    private final TextField user;
    private final PasswordField password;
    private final Button loginButton;
    private static Label label;

    public SimpleLoginView() {
        setSizeFull();
	
        user = new TextField("User:");
        user.setWidth("300px");
        user.setRequired(true);
        user.setInputPrompt("Your username");
        //user.addValidator(new EmailValidator("Username must be an email address"));
        user.setInvalidAllowed(false);

        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.addValidator(new PasswordValidator());
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");

        loginButton = new Button("Login", this);
        loginButton.addStyleName("primary"); 
        loginButton.setClickShortcut(KeyCode.ENTER);
        
        label = new Label();
        label.setValue("");
        
        VerticalLayout fields = new VerticalLayout(user, password, loginButton, label);
        fields.setCaption("Login in (correct is : fooo / hesloheslo0)");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // focus the username field when user arrives to the login view
        user.focus();
    }

    // Validator for validating the passwords
    private static final class PasswordValidator extends AbstractValidator<String> {

        public PasswordValidator() {
            super("The password provided is not valid");
        }

        @Override
        protected boolean isValidValue(String value) {
            // Password must be at least 8 characters long and contain at least one number
            if (value != null  && (value.length() < 8 || !value.matches(".*\\d.*"))) {
            	
                label.setValue("WRONG CREDENTIALS, try again");
                return false;
                
            }
            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    @Override
    public void buttonClick(ClickEvent event) {

        if (!user.isValid() || !password.isValid()) {
            return;
        }

        String username = user.getValue();
        String password = this.password.getValue();
      
        if (username.equals("fooo") && password.equals("hesloheslo0")) {

        	System.out.println("Correct login");
            getSession().setAttribute("user", username);
            getUI().getNavigator().navigateTo(ChartMainView.NAME);//

        } else {
        	
        	System.out.println("Wrong login");
        	label.setValue("WRONG CREDENTIALS, try again");
            this.password.setValue(null);
            this.password.focus();

        }
    }
}