/*
Copyright 2015 Elias Carter

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.example.elias.androidbuzzer;

import java.util.Stack;

public class StatisticsModel {

    public StatisticsModel(){
        singlePlayerReactionTimesMs = new Stack<>();
        twoPlayerFirstBuzzer = new Stack<>();
        threePlayerFirstBuzzer = new Stack<>();
        fourPlayerFirstBuzzer = new Stack<>();
    }

    //TODO(revamp model? what if you could have more than 4 players?)
    public Stack<Integer> singlePlayerReactionTimesMs;
    public Stack<Integer> twoPlayerFirstBuzzer;
    public Stack<Integer> threePlayerFirstBuzzer;
    public Stack<Integer> fourPlayerFirstBuzzer;
}

