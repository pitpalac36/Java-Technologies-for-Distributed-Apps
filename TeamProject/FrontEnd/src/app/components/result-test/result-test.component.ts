import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-result-test',
  templateUrl: './result-test.component.html',
  styleUrls: ['./result-test.component.css']
})
export class ResultTestComponent implements OnInit {

  public result = 0;

  constructor(
  ) { }

  ngOnInit(): void {
    if(sessionStorage.getItem("Result")){
      this.result = parseInt(sessionStorage.getItem("Result"));
    }
  }

}
