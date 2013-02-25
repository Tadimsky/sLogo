package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

class InputField extends JTextField implements FocusListener {

    private static final String HINT = "Command";
    private static final Font HINT_FONT = new Font("Sans-Serif",Font.ITALIC,14);
    private static final Font REGULAR_FONT = new Font("Helvetica", Font.PLAIN, 14);

    public InputField(final int fieldSize) {
        super(HINT, fieldSize);
        super.setFont(HINT_FONT);
        super.addFocusListener(this);
        // Get the command after press enter
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = getText();
                // test
                System.out.println(command);
            }
        });
    }

    @Override
    public void focusGained(FocusEvent e) {
        super.setFont(REGULAR_FONT);
        if(this.getText().isEmpty()) {
            super.setText("");
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setFont(HINT_FONT);
            super.setText(HINT);
        }
    }
    
    @Override
    public void setText(String text) {
        super.setText(text);
        if(text.isEmpty()) {
            super.setFont(HINT_FONT);
            super.setText(HINT);
        }
    }

    @Override
    public String getText() {
        String typed = super.getText();
        return typed.equals(HINT) ? "" : typed;
    }
}
