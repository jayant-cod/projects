import 'package:flutter/material.dart';

class Answer extends StatelessWidget {
  final Function select;
  final String answers;

  Answer(this.select, this.answers);
  @override
  Widget build(BuildContext context) {
    return Container(
      //width: double.infinity,
      child: RaisedButton(
        child: Text(
          'Red',
          style: TextStyle(color: Colors.white),
        ),
        onPressed: select,
        color: Colors.red[200],
      ),
    );
  }
}
