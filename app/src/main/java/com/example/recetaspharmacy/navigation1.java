package com.example.recetaspharmacy;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.sql.Statement;

import de.hdodenhof.circleimageview.CircleImageView;

public class navigation1 extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        NavigationView.OnNavigationItemSelectedListener,menu1.OnFragmentInteractionListener,Conocenos.OnFragmentInteractionListener
{
    private CircleImageView PhotoImageView;
    private TextView NameTextView;
    private TextView EmailTextView;
    private TextView idTextView;
    private GoogleApiClient googleApiClient;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation1);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        mToogle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.Open,R.string.Close);
        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navcontent);
        navigationView.setNavigationItemSelectedListener(this);
        //cargamos un fragtment por defecto
        Fragment fragment=new menu1();
        getSupportFragmentManager().beginTransaction().add(R.id.main_content,fragment).commit();
        //login silencioso
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToogle.onOptionsItemSelected(item)){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

     @SuppressWarnings("StatementWithEmptyBody")
     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //condiciones para diferentes fragments
        int id=item.getItemId();
         Fragment miFragment=null;
         boolean fragentSeleccionado=false;
        if(id==R.id.db){
          miFragment=new menu1();
          fragentSeleccionado=true;
        }else if(id==R.id.tendencias){
            miFragment=new Conocenos();
            fragentSeleccionado=true;
        }else if(id==R.id.productos){

        }else if(id==R.id.recetas){
        }else if(id==R.id.record){
        }else if(id==R.id.salir){
            Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    if(status.isSuccess()){
                        goLogInScreem();

                    }else{
                        Toast.makeText(getApplicationContext(),"No se pudo revokar el acceso",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
        }

        if(fragentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_content,miFragment).commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr=Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            GoogleSignInResult result=opr.get();
            handleSigInResult(result);

        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSigInResult(googleSignInResult);
                }
            });


        } }
    private void handleSigInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount account= result.getSignInAccount();
            NavigationView navigationView = (NavigationView) findViewById(R.id.navcontent);
            View headerView = navigationView.getHeaderView(0);
            NameTextView = headerView.findViewById(R.id.nameText);
            EmailTextView = headerView.findViewById(R.id.EmailText);
            PhotoImageView = headerView.findViewById(R.id.PhotoImage);
            NameTextView.setText(account.getDisplayName());
            EmailTextView.setText(account.getEmail());
            Glide.with(this).load(account.getPhotoUrl()).into(PhotoImageView);
            //Log.d("RecetasPharmacy",account.getPhotoUrl().toString());

        }else{
            goLogInScreem();
        }
    }
    //logout
    // public void logOut(View view) {

    //  Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
    //      @Override
    //      public void onResult(@NonNull Status status) {
    //          if(status.isSuccess()){
    //              goLogInScreem();
    //          }else{
    //              Toast.makeText(getApplicationContext(),"No se pudo cerrar seccion ",Toast.LENGTH_SHORT).show();
    //          } }});

    //}

//revoke
   // public void revoke(View view) {
   //     Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
   //       @Override
    //       public void onResult(@NonNull Status status) {
   //          if(status.isSuccess()){
    //               goLogInScreem();
//
    //              }else{
    //              Toast.makeText(getApplicationContext(),"No se pudo revokar el acceso",Toast.LENGTH_SHORT).show();
    //          }
    //      }
    //  });
    //}

    public void  goLogInScreem() {
        Intent intent=new Intent(this,LoginActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}








