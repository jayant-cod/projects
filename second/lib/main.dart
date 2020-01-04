import 'package:flutter/material.dart';
import './question.dart';
import './answer.dart';

void main(List<String> args) {
  runApp(Myapp());
}

class Myapp extends StatefulWidget {
  @override
  _MyappState createState() => _MyappState();
}

class _MyappState extends State<Myapp> {
  int index = 0;

  static const questions = [
    {
      'question': 'what is your favourite colour?',
      'answers': ['Red', 'Blue', 'Yellow']
    },
    {
      'question': 'choose shape',
      'answers': ['circle', 'square', 'triangle']
    },
    {
      'question': 'choose animal',
      'answers': ['cow', 'goat','boar']
    }
  ];

  void _getIndex() {
    setState(() {
      index++;
    });
    print(index);
    if (index < questions.length) {
      print('there is question');
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('choosing game'),
          backgroundColor: Colors.red[200],
        ),
        body: index < questions.length
            ? Column(
                children: [
                  Question(
                    questions[index]['question'],
                  ),
                  ...(questions[index]['answers'] as List<String>)
                      .map((answer) {
                    return Answer(_getIndex, answer);
                  }).toList()
                ],
              )
            : Center( 
                child: Text('End'),
              ),
      ),
    );
  }
}