package ar.edu.uner.prestabook.jframe.validation;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class ValidationFields {
    /**
     * Methods that indicate if the value entered in the fields is correct
     **/
    
    public void validationEmail(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (varilarCorreo(field.getText())) {
                    field.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));
                } else {
                    field.setBorder(new MatteBorder(1, 1, 1, 1, new Color(170, 0, 0)));
                }

            }
        });
    }
    
    public void validationNumber(JTextField number, JLabel lblNroNoValido) {
        number.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (varilarTelefono(number.getText())) {
                    if (number.getText().length() < 10) {
                        number.setBorder(new MatteBorder(1, 1, 1, 1, new Color(170, 0, 0)));
                        lblNroNoValido.setVisible(true);
                    } else {
                        number.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));
                        lblNroNoValido.setVisible(false);
                    }
                } else {
                    number.setBorder(new MatteBorder(1, 1, 1, 1, new Color(170, 0, 0)));
                    lblNroNoValido.setVisible(true);
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
                if (number.getText().length() >= 10) {
                    e.consume();
                }
            }
    
        });
    }

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
    
    public void validationGenericLetters(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validarGeneric(field.getText())) {
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
    
    public void validationPassword(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validarContrasenia(field.getText())) {

                    field.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 170, 0)));

                } else {
                    field.setBorder(new MatteBorder(1, 1, 1, 1, new Color(170, 0, 0)));
                }
            }
        });
    }
    
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
    
    public boolean varilarCorreo(String correo) {
        Pattern patron = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = patron.matcher(correo);

        return mat.find();
    }

    public boolean varilarTelefono(String telefono) {
        Pattern patron = Pattern.compile("^([(]0345[)]\s154|[+]5493454|3454)[0-9]{6}$");
        Matcher mat = patron.matcher(telefono);

        return mat.find();
    }

    public boolean validarGeneric(String field) {
        Pattern patron = Pattern.compile("^[A-Za-z]+$");
        Matcher mat = patron.matcher(field);

        return mat.find();
    }

    public boolean validarContrasenia(String contrasenia) {
        Pattern patron = Pattern.compile(
                "^(?=.*[0-9])(?=.*[az])([AZ]?)(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$");
        Matcher mat = patron.matcher(contrasenia);

        return mat.find();
    }
}
