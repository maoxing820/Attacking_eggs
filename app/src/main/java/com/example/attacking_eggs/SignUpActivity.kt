package com.example.attacking_eggs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private var shouldAutoSplit = true
    // 手机开头三位数
    private val phoneStartNumber = arrayOf(
        "133","149","153","173","177","180","181","189","199",
        "130","131","132","145","155","156","166","171","175","176","185","186","166",
        "134","135","136","137","138","139","147","150","151","152","157","158","159",
        "159","172","178","182","183","184","187","188","198"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editTextPhone.addTextChangedListener(object : LoginTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                // 判断按钮是否可以点击
                mSend.isEnabled = p0.toString().length == 13
                // 判断是在删除还是输入
                if (!shouldAutoSplit)return
                p0.toString().length.also {
                    if (it == 3 || it == 8){
                        // 自动添加空格-
                        p0?.append(' ')
                    }
                }
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                shouldAutoSplit = (p3 == 1)
            }
        })

        mSend.setOnClickListener {
            if (isPhoneTrue(editTextPhone.text.toString())){
                Toast.makeText(this,"正在请求验证码", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"当前手机号号段无法识别，请重新输入", Toast.LENGTH_LONG).show()
            }
        }

        SignupNextBtn.setOnClickListener {
            Intent().apply {
                setClass(this@SignUpActivity, InitInfo::class.java)
                putExtra("phoneNumber", editTextPhone.text.toString())
                startActivity(this)
            }
        }

        BackLoginBtn.setOnClickListener {
            Intent().apply {
                setClass(this@SignUpActivity,MainActivity::class.java)
                startActivity(this)
            }
        }

    }
    // 将格式化的内容转化为正常数据
    private fun getPhoneNumber(editable: Editable): String{
        // 创建一个新的对象 用于操作editable对象里面的内容
        SpannableStringBuilder(editable.toString()).apply {
            delete(3,4)
            delete(7,8)
            return this.toString()
        }
    }

    // 判断手机号是否符合规格
    private fun isPhoneTrue(phone: String): Boolean{
        for (startNumber in phoneStartNumber){
            if (phone.substring(0,3) == startNumber)
                return true
        }
        return false
    }

    open class LoginTextWatcher: TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    }
}