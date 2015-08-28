package View;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultCaret;

public class TextArea {

	private JTextArea text=new JTextArea(4, 20);
	private JScrollPane scroll=new JScrollPane(text);
	DefaultCaret caret = (DefaultCaret)text.getCaret();
	
	// Fa scorrere in automatico il pannello verso il basso
	public TextArea () {
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}
	
	public JTextArea getTextArea () {
		return text;
	}
	
	public JScrollPane getScrollPanel() {
		return scroll;
	}

}
