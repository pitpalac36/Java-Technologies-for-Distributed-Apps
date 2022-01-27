import { Component, OnInit } from '@angular/core';
import { Test } from 'src/app/models/test';
import { ServiceTestService } from 'src/app/services/service-test.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  public numberTest = 0;
  public isAvailable = false;
  public isVisible = true;
  public sizeAnswer;
  public answers;
  public isCheckAnswer = false;
  public currentQuestionIndex = 0;

  test = {
    "id": 1,
    "questions": [
      { id: 1, description: "fefefefe ?", isHidden: false },
      { id: 2, description: "cececec ?", isHidden: true },
      { id: 3, description: "bebebebeb ?", isHidden: true }
    ]
  };


  constructor(
    private service: ServiceTestService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.answers = new Array();

  }

  generateRandom() {
    this.isVisible = false;
    this.numberTest = Math.round(Math.random() * (3 - 1) + 1);
    this.service.getTest(this.numberTest).subscribe(test => {
      this.test = test;
    });
    setTimeout(function () {
      this.isAvailable = true;
      this.isVisible = true;
    }.bind(this), 3000);
  }


  sendAnswer(id: number, answer: boolean) {
    var dict = {}
    dict['id'] = id;
    dict['answer'] = answer;
    this.answers.push(dict);
  }

  sendResults(): any {
    if (this.answers.length != 5) {
      alert("Nu ati raspuns la intrebare.");
      this.isCheckAnswer = true;
    }
    else {
      this.isCheckAnswer = false;
      this.service.sendAnswers(this.answers)
      .subscribe(
        () => {
          this.router.navigate(['/result']);
        },
        () => {
          alert("Eroare.");
        }
      );
    }
    
  }

  checkIfAnswered(i: number) {
    if (this.answers.length != i+1) {
      alert("Nu ati raspuns la intrebare.");
      this.isCheckAnswer = true;
    }
    else {
      this.isCheckAnswer = false;
      this.test.questions[i].isHidden = true;
      this.test.questions[i + 1] ? this.test.questions[i + 1].isHidden = false : false
    }
  }

}
