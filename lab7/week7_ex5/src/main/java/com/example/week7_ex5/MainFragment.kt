import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.week7_ex5.Onefragment
import com.example.week7_ex5.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_main, container, false)
        val fragButton = view.findViewById<AppCompatButton>(R.id.frag_btn)
        val fragmentManager = requireActivity().supportFragmentManager
        var onClick = false
        fragButton.setOnClickListener {
            if (onClick) {
                onClick = false
                val transaction = fragmentManager.beginTransaction()
                val frameLayout = requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_content)
                transaction.remove(frameLayout!!).commit()
            }else {
                onClick = true
                val transaction = fragmentManager.beginTransaction()
                transaction.add(R.id.fragment_content,Onefragment()).commit()
            }
        }
        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val viewPager: ViewPager2 = requireActivity().findViewById(R.id.viewpager)
//        viewPager.isUserInputEnabled = true
//    }
}
