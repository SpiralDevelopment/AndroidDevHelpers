object DialogHelper {

    fun showDialog(
        context: Context
    ) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_error, null)
        val dialog: AlertDialog = with(AlertDialog.Builder(context)) {
            setCancelable(true)
            setView(view)
        }.show()

        view.findViewById<TextView>(R.id.someTV).text = title
        view.findViewById<TextView>(R.id.someBtn).apply {
            visibility = View.VISIBLE
            setOnClickListener {
                dialog.dismiss()
            }
        }

    }
}