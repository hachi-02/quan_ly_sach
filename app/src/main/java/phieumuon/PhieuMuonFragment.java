package phieumuon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

public class PhieuMuonFragment extends Fragment {
    RecyclerView rcv;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inlflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inlflater.inflate(R.layout.fragment_phieumuon, container, false);
        rcv = v.findViewById(R.id.rcv);
        return v;
    }
}
