import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String output = '0';
  double num1 = 0.0;
  double num2 = 0.0;
  int num3 = 0;
  String _output = '0';
  String oprand = "";

  buttonpressed(String number) {
    if (number == 'C') {
      _output = '0';
      num1 = 0.0;
      num2 = 0.0;
      oprand = '';
    } else if (number == '<-') {
      num3 = int.parse(_output);
      num3 = (num3 / 10).toInt();
      _output = num3.toString();
    } else if (number == '+' ||
        number == '-' ||
        number == '/' ||
        number == '%' ||
        number == 'x') {
      num1 = double.parse(output);
      oprand = number;
      _output = "0";
    } else if (number == '.') {
      if (_output.contains(".")) {
        print('Already have the dot operator');
        return;
      } else {
        _output = _output + number;
      }
    } else if (number == '=') {
      num2 = double.parse(output);
      if (oprand == '+') {
        print('addition');
        _output = (num1 + num2).toString();
      }
      if (oprand == '-') {
        _output = (num1 - num2).toString();
      }
      if (oprand == '/') {
        _output = (num1 / num2).toString();
      }
      if (oprand == 'x') {
        _output = (num1 * num2).toString();
      }
      if (oprand == '%') {
        _output = (num1 % num2).toString();
      }

      num1 = 0;
      num2 = 0;
      oprand = '';
    } else {
      _output = _output + number;
    }
    print(_output);

    setState(() {
      output = double.parse(_output).toStringAsFixed(2);
    });
  }

  Widget expandrow(String number) {
    return Expanded(
      child: OutlineButton(
        child: Text(
          number,
          style: TextStyle(fontSize: 60,color: Colors.blueGrey),
        ),
        onPressed: () {
          buttonpressed(number);
        },
        color: Colors.blueGrey,
        textColor: Colors.white,
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Calculator',
        theme: ThemeData(
          primarySwatch: Colors.grey,
        ),
        home: Scaffold(
            appBar: AppBar(
              title: Text('Calculator'),
            ),
            body: Container(
              child: Column(
                children: [
                  Container(
                    padding: EdgeInsets.symmetric(vertical: 20, horizontal: 20),
                    alignment: Alignment.bottomRight,
                    child: Text(
                      output,
                      style: TextStyle(
                        fontSize: 50,
                      ),
                    ),
                  ),
                  new Expanded(
                    child: Divider(),
                  ),
                  Column(children: [
                    Row(children: [
                      expandrow('C'),
                      expandrow('%'),
                      expandrow('<-'),
                      expandrow('/')
                    ]),
                    Row(children: [
                      expandrow('7'),
                      expandrow('8'),
                      expandrow('9'),
                      expandrow('x')
                    ]),
                    Row(children: [
                      expandrow('4'),
                      expandrow('5'),
                      expandrow('6'),
                      expandrow('-')
                    ]),
                    Row(children: [
                      expandrow('1'),
                      expandrow('2'),
                      expandrow('3'),
                      expandrow('+')
                    ]),
                    Row(children: [
                      expandrow('0'),
                      expandrow('00'),
                      expandrow('.'),
                      expandrow('=')
                    ]),
                  ]),
                ],
              ),
            )));
  }
}
