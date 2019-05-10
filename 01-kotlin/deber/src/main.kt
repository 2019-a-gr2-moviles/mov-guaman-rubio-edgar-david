import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

fun main(args: Array<String>) {

    val textArea = JTextArea()
    textArea.setText("Hello, Kotlin/Swing world")
    val scrollPane = JScrollPane(textArea)

    val frame = JFrame("Hello, Kotlin/Swing")
    frame.getContentPane().add(scrollPane, BorderLayout.CENTER)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setSize(Dimension(600, 400))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)

}