package com.example.mywords;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText txtWords;
    TextView txtSize;
    Button btnBig;
    Button btnSmall;
    CheckBox cbxBold;
    CheckBox cbxUnderline;
    Spinner sColor;
    Spinner sFont;
    RadioButton rbLTR;
    RadioButton rbRTL;
    EditText txtFileName;
    Button btnNew;
    Button btnSave;
    Button btnGetFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtWords = findViewById(R.id.txtWords);
        txtSize = findViewById(R.id.txtSize);
        btnBig = findViewById(R.id.btnBig);
        btnSmall = findViewById(R.id.btnSmall);
        cbxBold = findViewById(R.id.cbxBold);
        cbxUnderline = findViewById(R.id.cbxUnderline);
        sColor = findViewById(R.id.spinnerColor);
        sFont = findViewById(R.id.spinnerFont);
        rbLTR = findViewById(R.id.rbLTR);
        rbRTL = findViewById(R.id.rbRTL);
        txtFileName = findViewById(R.id.txtFileName);
        btnNew = findViewById(R.id.btnNew);
        btnSave = findViewById(R.id.btnSave);
        btnGetFile = findViewById(R.id.btnGetFile);

        fillColors();
        fillFonts();

        btnBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSize('+');
            }
        });
        btnSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSize('-');
            }
        });
        cbxBold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setFont();

            }
        });
        cbxUnderline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setUnderLine();
            }
        });
        sColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setColor();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sFont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setFont();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rbLTR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setAlign();
            }
        });
        rbRTL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setAlign();
            }
        });
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFile();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile();
            }
        });
        btnGetFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFile();
            }
        });
    }

    /**
     * method to set colors for the color spinner
     */
    protected void fillColors() {
        String[] strColors =
                {"black", "Red", "Green", "Blue", "Orange", "brown", "yellow", "Pink", "gray"};
        ArrayAdapter<String> adabter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, strColors);

        sColor.setAdapter(adabter);

    }

    /**
     * method to set fonts for the font spinner
     */
    protected void fillFonts() {

        String[] strFonts =
                {"Castelar", "Aldhabi", "Andlso", "Itcblka", "Bradhit", "Cambria"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, strFonts);
        sFont.setAdapter(adapter1);
    }

    /**
     * to set the font size
     * @param lagOrSmall
     */
    protected void setSize(char lagOrSmall) {
        int size = Integer.parseInt(txtSize.getText().toString());
        if (lagOrSmall == '+') size++;
        else size--;

        if (size > 50) size = 50;
        if (size < 6) size = 6;

        txtWords.setTextSize(size);
        txtSize.setText(size+"");
    }

    /*protected void smallText(){
        int size = Integer.parseInt(txtSize.getText()+"");
        size--;
        txtWords.setTextSize(size);
        txtSize.setText(size + "");
    }

     */

    /* protected void setBold() {

               setFont();
    }*/

    /**
     * to set the under line function
     */
    protected void setUnderLine() {
        if (cbxUnderline.isChecked()) {
            txtWords.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        } else {
            txtWords.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
        }


    }

    /**
     * to set the txt color
     */
    protected void setColor() {
        String strColor = sColor.getSelectedItem().toString();
        switch (strColor) {
            case "black":
                txtWords.setTextColor(getResources().getColor(R.color.black));
                break;
            case "Red":
                txtWords.setTextColor(getResources().getColor(R.color.Red));
                break;
            case "Green":
                txtWords.setTextColor(getResources().getColor(R.color.Green));
                break;
            case "Blue":
                txtWords.setTextColor(getResources().getColor(R.color.Blue));
                break;
            case "Orange":
                txtWords.setTextColor(getResources().getColor(R.color.Orange));
                break;
            case "brown":
                txtWords.setTextColor(getResources().getColor(R.color.brown));
                break;
            case "yellow":
                txtWords.setTextColor(getResources().getColor(R.color.yellow));
                break;
            case "Pink":
                txtWords.setTextColor(getResources().getColor(R.color.Pink));
                break;
            case "gray":
                txtWords.setTextColor(getResources().getColor(R.color.gray));
                break;
        }


    }

    /**
     * to set the txt font
     */
    protected void setFont() {
        String strFont = sFont.getSelectedItem().toString();

        Typeface tf;

        if ("sanfs_serif".equals(strFont))
            tf = Typeface.SANS_SERIF;
        else
            tf = Typeface.createFromAsset(getAssets(), strFont + ".ttf");


        /*switch (strFont){

            case "sans_serif":
                tf = Typeface.SANS_SERIF;
                break;
            case "Castelar":
                tf = Typeface.createFromAsset(getAssets(), "Castelar.ttf");
                break;
            case "Aldhabi":
                tf = Typeface.createFromAsset(getAssets(),"Aldhabi.ttf");
                break;
            case "Andlso":
                tf = Typeface.createFromAsset(getAssets(),"Andlso.ttf");
                break;
            case "Itcblkad":
                tf = Typeface.createFromAsset(getAssets(), "Itcblka.ttf");
                break;
            case "Bradhitc":
                tf = Typeface.createFromAsset(getAssets(), "Bradhit.ttf");
                break;
            case "Cambria":
                tf = Typeface.createFromAsset(getAssets(),"Cambria.ttf");
                break;
            default:
        }*/

        if (cbxBold.isChecked())
            txtWords.setTypeface(tf, Typeface.BOLD);
        else
            txtWords.setTypeface(tf, Typeface.NORMAL);
    }

    /**
     * to set txt align 
     */
    protected void setAlign() {
        if (rbLTR.isChecked()) {
            txtWords.setGravity(Gravity.LEFT);
        } else
            txtWords.setGravity(Gravity.RIGHT);
    }

    protected void newFile() {

        txtWords.setText("");
        txtSize.setText("14");
        txtWords.setTextSize(14);
        cbxBold.setChecked(false);
        cbxUnderline.setChecked(false);
        sColor.setSelection(0);
        sFont.setSelection(0);
        rbLTR.setChecked(false);
        rbRTL.setChecked(false);
        txtFileName.setText("File Name");
        txtWords.requestFocus();


    }

    protected void saveFile() {
        if ("".equals(txtFileName.getText().toString().trim())) {

            Toast.makeText(this, "Enter File Name!", Toast.LENGTH_LONG).show();
            txtFileName.requestFocus();
        } else {
            try {
                String strPath = getExternalFilesDir(null).getPath() + "/MyWords";
                File f = new File(strPath);
                f.mkdir();


                PrintWriter pw = new PrintWriter(strPath + "/" + txtFileName.getText() + ".txt");
                pw.write(txtWords.getText().toString());
                pw.close();

                PrintWriter pwSet = new PrintWriter(strPath + "/" + txtFileName.getText() + "Set.txt");
                String strSet =
                        txtSize.getText()
                                + "\n" + cbxBold.isChecked()
                                + "\n" + cbxUnderline.isChecked()
                                + "\n" + sColor.getSelectedItem()
                                + "\n" + sFont.getSelectedItem()
                                + "\n" + rbLTR.isChecked()
                                + "\n" + rbRTL.isChecked();
                pwSet.write(strSet);
                pwSet.close();


                Toast.makeText(this, "file Saved", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }

    protected void getFile() {
        try {
            String strPath = getExternalFilesDir(null).getPath() + "/MyWords";
            FileReader fr = new FileReader(strPath + "/" + txtFileName.getText() + ".txt");
            BufferedReader br = new BufferedReader(fr);
            String strContent = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                strContent += line + "\n";
            }
            fr = new FileReader(strPath + "/" + txtFileName.getText() + "Set.txt");
            br = new BufferedReader(fr);
            String[] strSet = new String[7];
            int x = 0;
            while ((line = br.readLine()) != null) {
                strSet[x] = line;
                x++;
            }
            fr.close();
            br.close();

            txtSize.setText(strSet[0]);
            txtWords.setTextSize(Integer.parseInt(strSet[0]));
            cbxBold.setChecked(Boolean.parseBoolean(strSet[1]));
            cbxUnderline.setChecked(Boolean.parseBoolean(strSet[2]));
            sColor.setSelection(((ArrayAdapter<String>) sColor.getAdapter()).getPosition(strSet[3]));
            sFont.setSelection(((ArrayAdapter<String>) sFont.getAdapter()).getPosition(strSet[4]));
            if ("true".equals(strSet[5])) rbLTR.setChecked(true);
            else rbRTL.setChecked(true);

            txtWords.setText(strContent);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}