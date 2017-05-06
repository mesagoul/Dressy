package com.lmesa.dressy.helpers;

import android.content.Context;
import android.widget.Toast;

import com.lmesa.dressy.R;
import com.lmesa.dressy.interfaces.ServiceListener;

/**
 * Created by Lucas on 17/04/2017.
 */

public class ResponseHttp{private Context context;
    public ResponseHttp(Context context){this.context = context;
    }

    public void onErrorGetUser(){
        Toast.makeText(context,context.getResources().getString(R.string.errorGetUser),Toast.LENGTH_SHORT).show();
    }
    public void onErrorCreateUser(){
        Toast.makeText(context,context.getResources().getString(R.string.errorCreateUser),Toast.LENGTH_SHORT).show();
    }
    public void onErrorGetClothe(){
        Toast.makeText(context,context.getResources().getString(R.string.errorGetClothe),Toast.LENGTH_SHORT).show();
    }
    public void onErrorCreateClothe(){
        Toast.makeText(context,context.getResources().getString(R.string.errorCreateClothe),Toast.LENGTH_SHORT).show();
    }
    public void onErrorDeleteClothe(){
        Toast.makeText(context,context.getResources().getString(R.string.errorDeleteClothe),Toast.LENGTH_SHORT).show();
    }
    public void onErrorManageClothes(){
        Toast.makeText(context,context.getResources().getString(R.string.errorManageClothes),Toast.LENGTH_SHORT).show();
    }
    public void onErrorGetClothes(){
        Toast.makeText(context,context.getResources().getString(R.string.errorGetClothes),Toast.LENGTH_SHORT).show();
    }
    public void onErrorCreateClothes(){
        Toast.makeText(context,context.getResources().getString(R.string.errorCreateClothes),Toast.LENGTH_SHORT).show();
    }
    public void onErrorDeleteClothes(){
        Toast.makeText(context,context.getResources().getString(R.string.errorDeleteClothes),Toast.LENGTH_SHORT).show();
    }
    public void onErrorManageClothe(){
        Toast.makeText(context,context.getResources().getString(R.string.errorManageClothe),Toast.LENGTH_SHORT).show();
    }
    public void onErrorGetSimilarity(){
        Toast.makeText(context,context.getResources().getString(R.string.errorGetSimilarity),Toast.LENGTH_SHORT).show();
    }
    public void onErrorCreatePost(){
        Toast.makeText(context,context.getResources().getString(R.string.errorCreatePost),Toast.LENGTH_SHORT).show();
    }

    public void onErrorGetPropertiesClothe() {
        Toast.makeText(context,context.getResources().getString(R.string.errorGetClotheProperties),Toast.LENGTH_SHORT).show();
    }

    public void onErrorGetPost() {
        Toast.makeText(context,context.getResources().getString(R.string.errorGetPost),Toast.LENGTH_SHORT).show();

    }
}
