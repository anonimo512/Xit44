package com.xiteyww.demo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private LinearLayout root;
    private int accent = Color.rgb(230, 55, 75);

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showPanel();
    }

    private GradientDrawable shape(int color, int radius, int strokeColor) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        if (strokeColor != 0) d.setStroke(2, strokeColor);
        return d;
    }

    private TextView text(String s, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setPadding(12, 8, 12, 8);
        return t;
    }

    private void addButton(LinearLayout box, String label, Runnable action) {
        Button b = new Button(this);
        b.setText(label);
        b.setAllCaps(false);
        b.setTextColor(Color.WHITE);
        b.setBackground(shape(accent, 18, 0));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 6, 0, 6);
        box.addView(b, lp);
        b.setOnClickListener(v -> action.run());
    }

    private void showPanel() {
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.rgb(13, 15, 23));
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(20, 24, 20, 24);
        scroll.addView(root);
        setContentView(scroll);

        TextView title = text("XITEYWW", 30, Color.WHITE, true);
        title.setGravity(Gravity.CENTER);
        root.addView(title);
        TextView subtitle = text("PAINEL DEMONSTRATIVO", 13, Color.LTGRAY, true);
        subtitle.setGravity(Gravity.CENTER);
        root.addView(subtitle);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(16, 16, 16, 16);
        card.setBackground(shape(Color.rgb(27, 30, 43), 22, Color.rgb(54, 59, 77)));
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1, -2);
        cp.setMargins(0, 22, 0, 12);
        root.addView(card, cp);

        card.addView(text("Configurações visuais", 20, Color.WHITE, true));
        card.addView(text("Escolha uma cor para o painel:", 14, Color.LTGRAY, false));
        addButton(card, "Tema vermelho", () -> { accent = Color.rgb(230,55,75); showPanel(); });
        addButton(card, "Tema azul", () -> { accent = Color.rgb(55,130,255); showPanel(); });
        addButton(card, "Tema roxo", () -> { accent = Color.rgb(155,90,235); showPanel(); });

        card.addView(text("Perfis de demonstração", 20, Color.WHITE, true));
        addButton(card, "Treinamento", () -> toast("Perfil Treinamento selecionado"));
        addButton(card, "Gladiadores", () -> toast("Perfil Gladiadores selecionado"));
        addButton(card, "Ranqueada", () -> toast("Perfil Ranqueada selecionado"));
        addButton(card, "Contra Squad", () -> toast("Perfil Contra Squad selecionado"));

        LinearLayout note = new LinearLayout(this);
        note.setOrientation(LinearLayout.VERTICAL);
        note.setPadding(16, 12, 16, 12);
        note.setBackground(shape(Color.rgb(35, 39, 52), 16, 0));
        root.addView(note);
        note.addView(text("Aviso", 17, Color.WHITE, true));
        note.addView(text("Este é um painel demonstrativo independente. Os botões alteram apenas a interface do aplicativo; não se conecta a jogos nem modifica jogos ou sistemas anti-cheat.", 13, Color.LTGRAY, false));
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
