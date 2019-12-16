package com.example.androidproj.repository


class RemoteRepository : Repository {


    private var sleep: Double= 0.0


    override fun getData(): String {

        for (i in 0..1000000){

            sleep++
            sleep *= 2

        }

        return "Hello world"
    }


}