package com.example.quoteoftheday;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView b1, b2, b3;
    private TextView t1;
    private ArrayList<String> quotes;
    private Random random;
    private String currentQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.addQQ);
        b2 = findViewById(R.id.shareQQ);
        b3 = findViewById(R.id.imageView);
        t1 = findViewById(R.id.textQQ);
        quotes = new ArrayList<>();
        random = new Random();
        AddQuotes();
        refreshQuote();
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshQuote();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareQuote();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });
    }

    private void AddQuotes() {
        quotes.add("Arise, awake, and stop not till the goal is reached. - Swami Vivekananda");
        quotes.add("The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi");
        quotes.add("Knowledge is power. - Chanakya");
        quotes.add("In a gentle way, you can shake the world. - Mahatma Gandhi");
        quotes.add("Life is a game, play it; Life is a challenge, meet it; Life is an opportunity, capture it. - Sri Sathya Sai Baba");
        quotes.add("The future depends on what you do today. - Mahatma Gandhi");
        quotes.add("When you are inspired by some great purpose, some extraordinary project, all your thoughts break their bonds. - Patanjali");
        quotes.add("Self-knowledge is the greatest wisdom. - Ramana Maharshi");
        quotes.add("Do your work with the welfare of others always in mind. - Bhagavad Gita");
        quotes.add("All power is within you; you can do anything and everything. - Swami Vivekananda");
    }

    private void refreshQuote() {
        if (!quotes.isEmpty()) {
            int index = random.nextInt(quotes.size());
            currentQuote = quotes.get(index);
            t1.setText(currentQuote);
        }
    }

    private void shareQuote() {
        if (currentQuote != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, currentQuote);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Share  via"));
        } else {
            Toast.makeText(this, "No quote to share", Toast.LENGTH_SHORT).show();
        }
    }

    private void Dialog() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.addqoutes);

        EditText quoteEditText = dialog.findViewById(R.id.AddQuotTT);
        Button addButton = dialog.findViewById(R.id.materialButton);
        Button cancelButton = dialog.findViewById(R.id.materialButton2);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newQuote = quoteEditText.getText().toString().trim();
                if (!newQuote.isEmpty()) {
                    quotes.add(newQuote);
                    refreshQuote();
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a quote", Toast.LENGTH_SHORT).show();
                }
            }


        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss() ;
                                            }
                                        }
               );

        dialog.show();
    }
}
