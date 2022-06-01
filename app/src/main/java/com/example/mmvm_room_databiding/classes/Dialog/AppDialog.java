package com.example.mmvm_room_databiding.classes.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.mmvm_room_databiding.R;
import com.example.mmvm_room_databiding.data.model.entities.WorkEntity;
import com.example.mmvm_room_databiding.databinding.DialogInsertWorkBinding;
import com.example.mmvm_room_databiding.databinding.DialogUpdateWorkBinding;
import com.example.mmvm_room_databiding.ui.main.MainActivity;

import java.util.Locale;

public class AppDialog {

    public static void createDialog(Context context, onListenClickDialog listen) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogInsertWorkBinding mBinding = DialogInsertWorkBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(mBinding.getRoot());
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        // set vi trí hiển thị dialog
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;

        // kich thước chiều ngang và chiều cao dialog
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        //set màu nền khi hiện dialog
        //window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen.onCancel();
                dialog.dismiss();
            }
        });

        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title=mBinding.edittextTitle.getText().toString().trim();
                String message=mBinding.edittextMessage.getText().toString().trim();

                listen.onSave(title,message);

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void createDialogDelete(Context context, onListenClickDelete listenClickDelete)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Thong bao");
        builder.setMessage("Ban co muon xoa cong viec nay ?");
        builder.setPositiveButton("dong y", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listenClickDelete.onDelete();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Huy",null);

        builder.show();
    }

    public static void createDialogUpdate(Context context, onListenClickUpdate listenClickUpdate)
    {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogUpdateWorkBinding mBinding = DialogUpdateWorkBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(mBinding.getRoot());
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        // set vi trí hiển thị dialog
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;

        // kich thước chiều ngang và chiều cao dialog
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        //set màu nền khi hiện dialog
        //window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));




        mBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenClickUpdate.onCancel();
                dialog.dismiss();
            }
        });

        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=mBinding.edittextTitle.getText().toString().trim();
                String message=mBinding.edittextMessage.getText().toString().trim();

                listenClickUpdate.onUpdate(title,message);

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public interface onListenClickDialog {
        void onCancel();

        void onSave(String title, String message);
    }

    public interface onListenClickDelete{
        void onDelete();
    }

    public interface onListenClickUpdate{
        void onCancel();
        void onUpdate(String title, String message);
    }
}
