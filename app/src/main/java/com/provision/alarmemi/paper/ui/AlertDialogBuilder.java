package com.provision.alarmemi.paper.ui;

import android.content.Context;
import android.content.Intent;

import com.provision.alarmemi.paper.ui.CustomAlertDialog;
import com.provision.alarmemi.paper.ui.CustomAlertDialog.CustomAlertDialogListener;

public class AlertDialogBuilder {
	AlertDialogBuilder(Context context, String title, String content,
			boolean cancelable, CustomAlertDialogListener listener) {
		Intent intent = new Intent(context, CustomAlertDialog.class);
		intent.putExtra("title", title);
		intent.putExtra("content", content);
		intent.putExtra("cancelable", cancelable);
		CustomAlertDialog.listener = listener;
		context.startActivity(intent);
	}

	public AlertDialogBuilder(Context context, int title, int content,
			boolean cancelable, CustomAlertDialogListener listener) {
		this(context, context.getString(title), context.getString(content),
				cancelable, listener);
	}

	AlertDialogBuilder(Context context, String title, int content,
			boolean cancelable, CustomAlertDialogListener listener) {
		this(context, title, context.getString(content), cancelable, listener);
	}

	public AlertDialogBuilder(Context context, int title, String content,
			boolean cancelable, CustomAlertDialogListener listener) {
		this(context, context.getString(title), content, cancelable, listener);
	}
}
