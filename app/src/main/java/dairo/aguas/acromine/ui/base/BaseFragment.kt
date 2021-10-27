package dairo.aguas.acromine.ui.base

import android.app.Dialog
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dairo.aguas.acromine.R

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
open class BaseFragment : Fragment() {

    private val dialogLoading by lazy {
        Dialog(this.requireContext()).apply {
            setContentView(
                layoutInflater.inflate(
                    R.layout.dialog_loading,
                    findViewById(android.R.id.content),
                    false
                )
            )
            window?.setBackgroundDrawableResource(R.color.transparent)
            setCancelable(false)
        }
    }

    fun showLoading() {
        if (dialogLoading.isShowing.not()) {
            dialogLoading.show()
        }
    }

    fun hideLoading() {
        dialogLoading.dismiss()
    }

    fun showMessageSnackBar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    fun showMessageToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}