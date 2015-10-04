package com.example.androidchoi.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GridLayout mGridLayout;
    EditText mEditText;
    Button mButton;
    int mNumberStack = 0;
    String mOperatorStack = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridLayout = (GridLayout)findViewById(R.id.gridLayout);
        mEditText = (EditText)findViewById(R.id.editText);

        // 1~9 숫자 버튼 이벤트 설정
        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView)v;
                mEditText.setText(mEditText.getText().toString() + textView.getText().toString());
            }
        };
        for(int i=0; i<9; i++)
        {
            int number = i+1;
            mButton = (Button)mGridLayout.getChildAt(i);
            mButton.setText("" + number);
            mButton.setOnClickListener(numberButtonListener);
        }

        // 0 버튼 이벤트 설정
        mButton = (Button)mGridLayout.getChildAt(10);
        mButton.setText("0");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEditText.getText().toString().equals(""))
                    return;
                TextView textView = (TextView)v;
                mEditText.setText(mEditText.getText().toString() + textView.getText().toString());
            }
        });

        mButton = (Button)mGridLayout.getChildAt(9);
        mButton.setText("C");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
                mNumberStack = 0;
                mOperatorStack = "";
            }
        });

        mButton = (Button)mGridLayout.getChildAt(11);
        mButton.setText("=");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = 0;
                if (mNumberStack == 0) return;
                else if(mOperatorStack.equals("+"))
                {
                    result = mNumberStack + Integer.parseInt(mEditText.getText().toString());
                }
                else if(mOperatorStack.equals("-"))
                {
                    result = mNumberStack - Integer.parseInt(mEditText.getText().toString());
                }
                else if(mOperatorStack.equals("*"))
                {
                    result = mNumberStack * Integer.parseInt(mEditText.getText().toString());
                }
                mEditText.setText("" + result);
                mNumberStack = 0;
                mOperatorStack = "";
            }
        });

        String operator[] = {"+", "-", "*"};
        for(int i =0; i<3; i++)
        {
            mButton = (Button)mGridLayout.getChildAt(12+i);
            mButton.setText(operator[i]);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mEditText.getText().toString().equals("")) return;

                    if (mOperatorStack.equals("")) {
                        TextView textView = (TextView) v;
                        mNumberStack = Integer.parseInt(mEditText.getText().toString());
                        mOperatorStack = textView.getText().toString();
                        mEditText.setText("");
                    }
                    else{

                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
