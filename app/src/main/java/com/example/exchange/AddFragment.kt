package com.example.exchange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.exchange.databinding.FragmentAddBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        firestore = FirebaseFirestore.getInstance()

        binding.buttonUpload.setOnClickListener {
            uploadBook()
        }

        return binding.root
    }

    private fun uploadBook() {
        val name = binding.editTextBookName.text.toString().trim()
        val className = binding.editTextClass.text.toString().trim()
        val subject = binding.editTextSubject.text.toString().trim()
        val price = binding.editTextPrice.text.toString().trim()
        val contact = binding.editTextSellerContact.text.toString().trim()

        if (name.isEmpty() || className.isEmpty() || subject.isEmpty() || price.isEmpty() || contact.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE

        val book = hashMapOf(
            "name" to name,
            "className" to className,
            "subject" to subject,
            "price" to price,
            "contact" to contact,
            "timestamp" to System.currentTimeMillis()
        )

        firestore.collection("books")
            .add(book)
            .addOnSuccessListener {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Book uploaded", Toast.LENGTH_SHORT).show()
                clearFields()
            }
            .addOnFailureListener {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Failed to upload", Toast.LENGTH_SHORT).show()
            }
    }

    private fun clearFields() {
        binding.editTextBookName.text?.clear()
        binding.editTextClass.text?.clear()
        binding.editTextSubject.text?.clear()
        binding.editTextPrice.text?.clear()
        binding.editTextSellerContact.text?.clear()
    }
}
