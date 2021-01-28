import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.widget.EditText

fun EditText.addPhoneTextWatcher() {
    this.addTextChangedListener(object : PhoneNumberFormattingTextWatcher() {
        private var backspacingFlag = false
        private var editedFlag = false
        private var cursorComplement: Int = 0

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            cursorComplement = s.length - this@addPhoneTextWatcher.selectionStart
            backspacingFlag = count > after
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable) {
            val string = s.toString()
            val phone = string.replace("[^\\d]".toRegex(), "")
            if (!editedFlag) {
                if (phone.length >= 8 && !backspacingFlag) {
                    editedFlag = true
                    val ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + " " + phone.substring(6, 8) + " " +phone.substring(8)
                    this@addPhoneTextWatcher.setText(ans)
                    this@addPhoneTextWatcher.setSelection(this@addPhoneTextWatcher.text.length - cursorComplement)
                } else if (phone.length >= 6 && !backspacingFlag) {
                    editedFlag = true
                    val ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + " " + phone.substring(6)
                    this@addPhoneTextWatcher.setText(ans)
                    this@addPhoneTextWatcher.setSelection(this@addPhoneTextWatcher.text.length - cursorComplement)
                } else if (phone.length >= 3 && !backspacingFlag) {
                    editedFlag = true
                    val ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3)
                    this@addPhoneTextWatcher.setText(ans)
                    this@addPhoneTextWatcher.setSelection(this@addPhoneTextWatcher.text.length - cursorComplement)
                }
            } else {
                editedFlag = false
            }
        }
    })
}
