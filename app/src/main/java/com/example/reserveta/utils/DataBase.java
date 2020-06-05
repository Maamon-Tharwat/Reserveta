package com.example.reserveta.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reserveta.R;
import com.example.reserveta.activities.DoctorActivity;
import com.example.reserveta.activities.MainActivity;
import com.example.reserveta.activities.PatientActivity;
import com.example.reserveta.model.DoctorModel;
import com.example.reserveta.model.PatientModel;
import com.example.reserveta.model.ReservationModel;
import com.example.reserveta.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class DataBase {


    private static FirebaseAuth auth;
    private static FirebaseUser user;

    private static FirebaseDatabase database;
    private static DatabaseReference reference;

    public static void signIn(final Context context, String username, String password) {
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(username, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        guide(context);
                        Toast.makeText(context, "Log in successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void signUp(final Context context, final UserModel userData) {
        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
            }
        });
        user = auth.getCurrentUser();
        auth.createUserWithEmailAndPassword(userData.getEmail(), userData.getPassword())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(context, "done1", Toast.LENGTH_SHORT).show();
                        user = auth.getCurrentUser();
                        userData.setUID(user.getUid());
                        database = FirebaseDatabase.getInstance();
                        reference = database.getReference().child(context.getString(R.string.user)).child(userData.getUID());
                        reference.setValue(userData);
                        auth.signOut();
                        Toast.makeText(context, "done2", Toast.LENGTH_SHORT).show();
                        guide(context);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "not done1", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void updateUser(Context context, DoctorModel user) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(context.getString(R.string.user)).child(user.getUID());
        reference.setValue(user);

    }


    public static void checkAuth(Context context) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user != null) {
            guide(context);
            Toast.makeText(context, "You already signed in", Toast.LENGTH_SHORT).show();
        }
    }

    private static void guide(final Context context) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent;
            intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return;
        }
        getCurrentUser(context, new UserCallback() {
            @Override
            public void onCallback(DoctorModel value) {
                Intent intent;
                intent = new Intent(context, DoctorActivity.class);
                intent.putExtra(context.getString(R.string.user), value);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            @Override
            public void onCallback(PatientModel value) {
                Intent intent;
                intent = new Intent(context, PatientActivity.class);
                intent.putExtra(context.getString(R.string.user), value);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    private static void getCurrentUser(final Context context, final UserCallback userCallback) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(context.getString(R.string.user)).child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(UserModel.class).getType().equals(context.getString(R.string.doctor))) {
                    userCallback.onCallback(dataSnapshot.getValue(DoctorModel.class));
                } else {
                    userCallback.onCallback(dataSnapshot.getValue(PatientModel.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void signOut(Context context) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user != null) {
            auth.signOut();
            context.startActivity(new Intent(context, MainActivity.class));
        } else {
            Toast.makeText(context, "You are Signed out", Toast.LENGTH_SHORT).show();
        }
    }

    public static void getAllSpecializationDoctors(final Context context, final String type, final RecyclerView.Adapter adapter, final ArrayList list) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(context.getString(R.string.user));
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getValue(UserModel.class).getType().equals(context.getString(R.string.doctor))) {
                    if (dataSnapshot.getValue(DoctorModel.class).getSpecialization().equals(type)) {
                        list.add(dataSnapshot.getValue(DoctorModel.class));
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void addReservation(Context context, DoctorModel doctor, PatientModel patient, long reservationTime) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(context.getString(R.string.reservations)).child(doctor.getUsername()).child(reservationTime + "");
        reference.setValue(new ReservationModel(doctor.getUID(), patient.getUID(), reservationTime, patient.getUsername()));
        doctor.setLastReservation(reservationTime);
        reference = database.getReference().child(context.getString(R.string.user)).child(doctor.getUID());
        reference.setValue(doctor);
    }

    public static void getDoctorReservations(Context context, DoctorModel doctor, RecyclerView.Adapter adapter, ArrayList list) {
        database = FirebaseDatabase.getInstance();
          reference = database.getReference().child(context.getString(R.string.reservations)).child(doctor.getUsername());
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ReservationModel model = dataSnapshot.getValue(ReservationModel.class);
                if (model.getReservationTime() >= Calendar.getInstance().getTimeInMillis()) {
                    list.add(model);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private interface UserCallback {
        void onCallback(DoctorModel value);

        void onCallback(PatientModel value);
    }


}
