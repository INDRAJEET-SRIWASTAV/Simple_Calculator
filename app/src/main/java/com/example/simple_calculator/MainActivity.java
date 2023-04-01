package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    TextView resultText, SolutionText;
    Button btnClear, btnopenBracket, btnCloseBracket, btnAC;
    Button btnDiv, btnMul, btnAdd, btnMinus, btnEqual;
    Button btn0, btn1, btn2,btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.text1);
        SolutionText = findViewById(R.id.result);

        AssignId(btnClear, R.id.btnClear);
        AssignId(btnopenBracket, R.id.btnopenBracket);
        AssignId(btnCloseBracket, R.id.btnCloseBracket);
        AssignId(btnAC, R.id.btnAC);
        AssignId(btnDiv, R.id.btnDiv);
        AssignId(btnMul, R.id.btnMul);
        AssignId(btnAdd, R.id.btnAdd);
        AssignId(btnMinus, R.id.btnMinus);
        AssignId(btnEqual, R.id.btnEqual);
        AssignId(btn0, R.id.btn0);
        AssignId(btn1, R.id.btn1);
        AssignId(btn2, R.id.btn2);
        AssignId(btn3, R.id.btn3);
        AssignId(btn4, R.id.btn4);
        AssignId(btn5, R.id.btn5);
        AssignId(btn6, R.id.btn6);
        AssignId(btn7, R.id.btn7);
        AssignId(btn8, R.id.btn8);
        AssignId(btn9, R.id.btn9);
        AssignId(btnDot, R.id.btnDot);


    }

    void AssignId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Button button = (Button) view;
        String ButtonText = button.getText().toString();
       String dataTocalculate = SolutionText.getText().toString();

       if(ButtonText.equals("AC")){
          SolutionText.setText(" ");
          resultText.setText("0");
          return;

       }
       if(ButtonText.equals("=")){
           //SolutionText.setText(resultText.getText());
           resultText.getText();

           return;
       }
       if(ButtonText.equals("C")){
           dataTocalculate = dataTocalculate.substring(0,dataTocalculate.length()-1);

       }else{
           dataTocalculate = dataTocalculate + ButtonText;
       }



       SolutionText.setText(dataTocalculate);



       String FinalResult = calculateResult(dataTocalculate);

       if(!FinalResult.equals("Error")){
           resultText.setText(FinalResult);
       }

    }

    String calculateResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String FinalResult = context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if(FinalResult.endsWith(".0")){
                FinalResult = FinalResult.replace(".0","");
            }
            return FinalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}