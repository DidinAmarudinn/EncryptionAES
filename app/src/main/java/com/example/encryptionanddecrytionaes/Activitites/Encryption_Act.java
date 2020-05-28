package com.example.encryptionanddecrytionaes.Activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.encryptionanddecrytionaes.Function.Decrypt;
import com.example.encryptionanddecrytionaes.Function.Encrypt;
import com.example.encryptionanddecrytionaes.R;
import com.example.encryptionanddecrytionaes.databinding.ActivityEncryptionBinding;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encryption_Act extends AppCompatActivity {
    ActivityEncryptionBinding binding;
    KeyGenerator keyGenerator;
    SecretKey secretKey;
    byte[] secretKeyen;
    String strSecretKey;
    byte[] IV = new byte[16];
    byte[] cipherText;
    SecureRandom random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEncryptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryption();
            }
        });
        binding.copyEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hasilEncrypt=binding.tvHasilEncrypt.getText().toString().trim();
                copyText(hasilEncrypt);
            }
        });
        binding.copyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iv=binding.tvIV.getText().toString().trim();
                copyText(iv);
            }
        });
        binding.copySk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sk=binding.tvSeccretKey.getText().toString().trim();
                copyText(sk);
            }
        });
        binding.btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrypt();
            }
        });
    }



    public void encryption(){
        String ChipperText=binding.etEncryption.getText().toString().trim();
        if (ChipperText.isEmpty()){
            binding.etEncryption.setError("Mohon isi field yang kosong");
        }else {
            try {
                 keyGenerator=KeyGenerator.getInstance("AES");

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            binding.llHasilEncrypt.setVisibility(View.VISIBLE);
            binding.llIv.setVisibility(View.VISIBLE);
            binding.llSk.setVisibility(View.VISIBLE);
            keyGenerator.init(256);
            secretKey =keyGenerator.generateKey();
            secretKeyen=secretKey.getEncoded();
            strSecretKey=encoderFun(secretKeyen);
            binding.tvSeccretKey.setText(strSecretKey);
            random=new SecureRandom();
            random.nextBytes(IV);
            try {
                cipherText= Encrypt.encrypt(binding.etEncryption.getText().toString().trim().getBytes(),secretKey,IV);
                String hasil= encoderFun(cipherText);
                binding.tvHasilEncrypt.setText(hasil);

                String iv=encoderFun(IV);
                binding.tvIV.setText(iv);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static String encoderFun(byte[] decval){
        String conVal= Base64.encodeToString(decval,Base64.DEFAULT);
        return conVal;
    }

    public void decrypt(){
        String dedcrypedText;
        if (binding.etSeccretText.getText().toString().isEmpty() || binding.etIvText.getText().toString().isEmpty()
                || binding.etEncryptext.getText().toString().isEmpty()){
            binding.etEncryptext.setError("Isi Field Ini");
            binding.etIvText.setError("Isi Field Ini");
            binding.etSeccretText.setError("Isi Field Ini");
        }else {
            try {
                binding.llHasilDecrypt.setVisibility(View.VISIBLE);
                String etEncryptext=binding.etEncryptext.getText().toString();
                String etIV=binding.etIvText.getText().toString();
                String etSK=binding.etSeccretText.getText().toString();
                Log.d("etEncypt",etEncryptext);
                Log.d("etIv",etIV);
                Log.d("etSk",etSK);
                byte[] enCrypText=decoderFun(etEncryptext);
                byte[] iv=decoderFun(etIV);
                byte[] seccretKey=decoderFun(etSK);
                SecretKey originalSecretKey=new SecretKeySpec(seccretKey,0,seccretKey.length,"AES");
                dedcrypedText= Decrypt.decrypt(enCrypText,originalSecretKey,iv);
                binding.tvHasilDecrypt.setText(dedcrypedText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public static byte[] decoderFun(String enval){
        byte[] conval=Base64.decode(enval,Base64.DEFAULT);
        return conval;
    }
    public void copyText(String copy){
        ClipboardManager clipboardManager=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data=ClipData.newPlainText("et",copy);
        clipboardManager.setPrimaryClip(data);
        Toast.makeText(Encryption_Act.this,"Copied "+data,Toast.LENGTH_SHORT).show();
    }
}
