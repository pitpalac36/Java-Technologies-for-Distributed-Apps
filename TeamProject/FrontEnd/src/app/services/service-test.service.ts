import { catchError, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Test } from '../models/test';

const headers = new HttpHeaders();
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
      
  })
};

@Injectable({
  providedIn: 'root'
})
export class ServiceTestService {
  private apiUrl = 'http://localhost:8080/test';  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': 'Content-Type',
    'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
     })
  }

  constructor(
    private http: HttpClient,
  ) { }

  getTest(numberTest: number): Observable<Test> {
    return this.http.post(this.apiUrl, {
      id: numberTest
    }).pipe(
      tap(response => {
        if(response){
          return response = this.mapToTest(response);
        }
      }),
      catchError(this.handleError<any>('getTest'))
    );
  }

  mapToTest(test: any) : Test{
    const result = {};
    result["id"] = test["testID"];
    result["questions"] = test["questions"];
    result["questions"].forEach(element => {
      element["isHidden"] = true;
    });
    result["questions"][0]["isHidden"] = false;
    return result as Test;
  }

  sendAnswers(answers: any): Observable<any> {
    const url = this.apiUrl + '/answer';
    console.log(answers);
    return this.http.post(url, {
      answers: answers
    }).pipe(
      tap(response => {
        if(response){
          sessionStorage.setItem("Result", response.toString());
        }
      }),
      catchError(this.handleError<any>('sendAnswers'))
    );
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      return of(result as T);
    };
  }
}
