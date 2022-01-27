import { AfterViewInit, Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {

 
  title = 'crud-operations-angular';
 
  constructor(
    
  ) {  }

  ngAfterViewInit(): void {
    throw new Error('Method not implemented.');
  }
  ngOnInit(){
    
  }
}
