package com.example.myapplication

import java.util.*

class CodingPractice {



    fun rotateArray(array:MutableList<Int>, step:Int):MutableList<Int>{
        val result:MutableList<Int> = mutableListOf<Int>()
        array.forEachIndexed{ index, value ->

            println(index)
            if(index < 1 && index == array.size)
            //result.add(array[index])

            else
                result.add(array[index])

        }
        return result

    }
    fun IntArray.leftShift(k:Int):IntArray{

        val shiftedArray = this.copyOf()
        var rotateBy = k

        if(rotateBy >size){
            rotateBy = rotateBy % size
        }

        forEachIndexed{ index, value ->
            val shiftedIndex = (index + (size - rotateBy)) % size
            shiftedArray[shiftedIndex] = value
        }
        return shiftedArray

    }
    fun IntArray.rightShift(k:Int):IntArray{
        val shiftedArray = this.copyOf()
        var rotateBy = k

        if(rotateBy > size){
            rotateBy = rotateBy % size
        }

        forEachIndexed{ index, value ->
            val shiftedIndex = (index + rotateBy) % size
            shiftedArray[shiftedIndex] = value
        }

        return shiftedArray
    }

    //return k largest value
    // it means second largest in sorted array when k is 2.
    fun findKthLargest(inputArray:IntArray, k:Int):Int{
        Arrays.sort(inputArray)
        return inputArray[inputArray.size -k]
    }

    /*
    //Given a 2D matrix, if an element is 0,
    //set its entire row and column to 0. Do it in place.

    Input:
    [ [1,1,1,1],
      [1,1,1,0],
      [1,1,1,1]]
    Output:
      [[1,1,1,0],
       [0,0,0,0],
       [1,1,1,0]]
    */

    //by Me
    fun setRowAndColumnToZero(given:Array<IntArray>):Array<IntArray>{
        var zeroIndex:Int? = null
        var rowIndex:Int? = null


        for(row in given){
            row.forEachIndexed{ index, value ->
                if(value == 0){
                    zeroIndex = index
                    rowIndex = given.indexOf(row)
                }
            }
        }

        //setRowAndColumn to Zero
        given.forEachIndexed{ thisRowIndex, intArray ->

            if(thisRowIndex == rowIndex){
                (0..intArray.size-1).forEachIndexed{ index, value ->
                    intArray.set(index, 0)
                }
            }
            else
                intArray[zeroIndex!!] = 0

        }


        //main()
        val givenMatrix = arrayOf(
            intArrayOf(1,1,1,1),
            intArrayOf(1,1,1,0),
            intArrayOf(1,1,1,1),
        )

        val resultMatrix = setRowAndColumnToZero(givenMatrix)
        resultMatrix.forEach{ eachIntArray->
            eachIntArray.forEachIndexed{ index, value ->
                if(index == eachIntArray.size -1)
                    print("$value \n")
                else
                    print(value)
            }

        }




        return given
    }
    fun setZero(matrix:Array<IntArray>):Unit{
        val rows = matrix.size
        val cols = matrix[0].size

        var firstRow = false
        var firstCol = false

        for (row in 0 until rows) {
            for (col in 0 until cols){

                if(matrix[row][col] == 0)
                {
                    if (row ==0){
                        firstRow = true

                    }
                    if(col ==0){
                        firstCol = true
                    }
                    matrix[row][0] = 0
                }
            }

            for(row in 1 until rows){
                if(matrix[row][0] == 0) {
                    for(col in 1 until cols){
                        matrix[row][col] = 0
                    }
                }
            }

            for (col in 1 until cols){
                if(matrix[0][col]==0){
                    for(row in 1 until rows){
                        matrix[row][col] = 0
                    }
                }
            }
            if(firstRow){
                for (col in 0 until cols){
                    matrix[0][col] = 0
                }
            }
            if(firstCol){
                for(row in 0 until rows){
                    matrix[row][0] = 0
                }
            }
        }

        //main()
        println(matrix.map{ it->
            it.toList()
        })
    }

    //----Spirial Maxtrix Print----
    /*
        Input:
    [ [1,2,3,4],
      [5,6,7,8],
      [9,10,11,12],
      [13,14,15,16]]
    Output:
        1 2 3 4 8 7 6 5 9 10 11 12 16 15 14 13
     */
    //by Me
    /*
        1. Even number of array print normally
        2. Odd Number of array print backword with using forloop

     */
    fun printOutSprialPatter(matrix:Array<IntArray>):Unit{


    }




}