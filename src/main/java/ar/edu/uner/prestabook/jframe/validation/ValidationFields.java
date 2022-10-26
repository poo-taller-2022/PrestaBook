package ar.edu.uner.prestabook.jframe.validation;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

/**
 * Class for the validation of the fields when registering
 *
 */
public class ValidationFields {

    /**
     * Modify the color of the Name field border depending on whether the
     * validation is correct
     * 
     * @param name
     **/
    public void validationName(JTextField name) {
        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validationPatternName(name.getText())) {
                    name.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));
                } else {
                    name.setBorder(new MatteBorder(1, 1, 1, 1, new Color(170, 0, 0)));
                }
            }
        });
    }

    /**
     * Modify the color of the Email field border depending on whether the
     * validation is correct
     * 
     * @param email
     **/
    public void validationEmail(JTextField email) {
        email.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validationPatternEmail(email.getText())) {
                    email.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));
                } else {
                    email.setBorder(new MatteBorder(1, 1, 1, 1, new Color(170, 0, 0)));
                }
            }
        });
    }

    /**
     * Modify the color of the Email field border depending on whether the
     * validation is correct
     * 
     * @param phoneNumber
     **/
    public void validationPhoneNumber(JTextField phoneNumber) {
        phoneNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validationPhone(phoneNumber.getText())) {
                    phoneNumber.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));
                } else {
                    phoneNumber.setBorder(new MatteBorder(1, 1, 1, 1, new Color(170, 0, 0)));
                }
            }
        });
    }

    /**
     * Modify the color of the phone number field border depending on whether the
     * validation is correct
     * 
     * @param number
     * @param lblNroNoValido
     **/
    public void validationNumber(JTextField number) {
        number.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));
        number.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (number.getText().length() >= 10) {
                    e.consume();
                }
            }
        });
    }

    /**
     * Modify the color of the border of the fields with string depending on whether
     * the validation is successful
     * 
     * @param field
     **/
    public void validationGenericString(JTextField field) {
        field.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (field.getText().length() >= 40) {
                    e.consume();
                }
            }
        });
    }

    /**
     * Modify the border color of fields with only letters depending on whether
     * validation is successful
     * 
     * @param field
     **/
    public void validationGenericLetters(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validationPatternGeneric(field.getText())) {
                    field.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));
                } else {
                    field.setBorder(new MatteBorder(1, 1, 1, 1, new Color(170, 0, 0)));
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (field.getText().length() >= 40) {
                    e.consume();
                }
            }
        });
    }

    /**
     * Modify the color of the document field border depending on whether the
     * validation is correct
     * 
     * @param document
     **/
    public void validationDocument(JTextField document) {
        document.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));
        document.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (document.getText().length() >= 10) {
                    e.consume();
                }
            }
        });
    }

    /**
     * Modify the color of the password field border depending on whether the
     * validation is correct
     * 
     * @param password
     **/
    public void validationPassword(JTextField password) {
        password.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));
        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (password.getText().length() >= 20) {
                    e.consume();
                }
            }
        });
    }

    /**
     * Modify the color of the repeat password field border depending on whether the
     * validation is correct
     * 
     * @param password
     * @param repeatPassword
     **/
    public void validationRepeatPassword(JTextField password, JTextField repeatPassword) {
        repeatPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (repeatPassword.getText().equals(password.getText())) {
                    repeatPassword.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));

                } else {
                    repeatPassword.setBorder(new MatteBorder(1, 1, 1, 1, new Color(170, 0, 0)));
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (repeatPassword.getText().length() >= 20) {
                    e.consume();
                }
            }
        });
    }

    /**
     * Validate the email field
     * 
     * @param email
     * @return boolean depending on whether it matches the pattern or not
     **/
    public boolean validationPatternEmail(String email) {
        Pattern patron = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = patron.matcher(email);
        return mat.find();
    }

    /**
     * Validate the field accepting only letters
     * 
     * @param textfield
     * @return boolean depending on whether it matches the pattern or not
     **/
    public boolean validationPatternGeneric(String textfield) {
        Pattern patron = Pattern.compile("^[A-Za-z]+$");
        Matcher mat = patron.matcher(textfield);
        return mat.find();
    }

    /**
     * Validate the name field
     * 
     * @param name
     * @return boolean depending on whether it matches the pattern or not
     **/
    public boolean validationPatternName(String name) {
        Pattern patron = Pattern.compile(
                "^[a-zA-ZàáâäãåąćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$");
        Matcher mat = patron.matcher(name);
        return mat.find();
    }

    /**
     * Validate the phone field
     * 
     * @param name
     * @return boolean depending on whether it matches the pattern or not
     **/
    public boolean validationPhone(String phone) {
        return phone.length() > 7 && phone.length() < 13;
    }
}
