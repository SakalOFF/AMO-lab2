package com.example.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception


class MyException(str: String): Exception(str)

class MainFragment : Fragment() {

    val RANDOM_SIZE = 65
    val RANDOM_RANGE = 1000

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.graph_button).setOnClickListener(this::graphAction)
        view.findViewById<Button>(R.id.random_button).setOnClickListener(this::generateRandomArray)
        view.findViewById<Button>(R.id.sort_button).setOnClickListener(this::sortAction)
    }

    private fun graphAction(vie: View){
        val result = Array<Number>(80) {0}
        for(i in (0 until 80)){
            val arr = Array(i * 100){(-RANDOM_RANGE..RANDOM_RANGE).random()}
            val start = System.nanoTime()
            boseNelson(arr)
            result[i] = (System.nanoTime() - start) / 1000000
        }
        val myAct: OnFragmentInteractionListener = activity as OnFragmentInteractionListener
        myAct.onFragmentInteraction(result)
    }

    private fun generateRandomArray(view: View){
        val arr = Array(RANDOM_SIZE){(-RANDOM_RANGE..RANDOM_RANGE).random()}
        array_edit.setText(arr.joinToString(" "))
    }

    private fun sortAction(view: View){
        try {
            val arrString = array_edit.text.trim().split(" ")
            var arr = Array(arrString.size) { i -> (arrString[i].toInt()) }
            arr = boseNelson(arr)
            array_edit.setText(arr.joinToString(" "))
        }
        catch (ex: Exception){
            val myToast = Toast.makeText(activity, "Вводити слід лише цілі числа через пробіл", Toast.LENGTH_SHORT)
            myToast.show()
        }
    }

    private fun boseNelson(_data: Array<Int>): Array<Int>{
        var j: Int
        var m = 1
        var data = _data
        while(m < data.size){
            j = 0
            while (j + m < data.size){
                data = boseNelsonMerge(j, m, m, data)
                j += m + m
            }
            m *= 2
        }
        return data
    }

    private fun boseNelsonMerge(j: Int, r: Int, _m: Int, _data: Array<Int>): Array<Int> {
        var m = _m
        var data = _data
        if(j + r < data.size){
            if(m == 1){
                if(data[j] > data[j+r]){
                    val extra = data[j]
                    data[j] = data[j+r]
                    data[j+r] = extra
                }
            }
            else{
                m /= 2
                data = boseNelsonMerge(j, r, m, data)
                if (j + r + m < data.size){
                    data = boseNelsonMerge(j + m, r, m, data)
                }
                data = boseNelsonMerge(j + m, r - m, m, data)
            }
        }
        return data
    }
}