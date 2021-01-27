import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import by.immo.dating.R;

public class CustomDialog extends Dialog {

    public interface OnReportSubmit {
        void onReportSubmitted(String name, String phone, String email, String reportText);
    }

    private Context context;
    private Boolean isAuthorized;
    private OnReportSubmit onReportSubmitListener;

    public ReportDialog(@NonNull Context context, Boolean isAuthorized, OnReportSubmit onReportSubmitListener) {
        super(context, R.style.ReportDialogDesign);
        this.context = context;
        this.isAuthorized = isAuthorized;
        this.onReportSubmitListener = onReportSubmitListener;
    }

    public ReportDialog builder() {
        initView();
        return this;
    }

    public void initView() {
        View view = View.inflate(context, R.layout.dialog_report, null);
        setContentView(view);

        ImageView close = view.findViewById(R.id.iv_close);
        Button btnClose = view.findViewById(R.id.btn_close);
        Button btnSubmit = view.findViewById(R.id.btn_submit_report);
        EditText reportDesc = view.findViewById(R.id.et_report);
        EditText name = view.findViewById(R.id.et_name);
        EditText phoneNum = view.findViewById(R.id.et_number);
        EditText email = view.findViewById(R.id.et_email);

        if (isAuthorized) {
            email.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
            phoneNum.setVisibility(View.GONE);
        }

        btnClose.setOnClickListener(v -> {
            dismiss();
        });

        close.setOnClickListener(v -> {
            dismiss();
        });

        btnSubmit.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(reportDesc.getText())) {
                onReportSubmitListener.onReportSubmitted(name.getText().toString(),
                        phoneNum.getText().toString(),
                        email.getText().toString(),
                        reportDesc.getText().toString());
            }
        });
    }
}
