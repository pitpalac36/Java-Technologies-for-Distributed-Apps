import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TestComponent } from './components/test/test.component';
import { ResultTestComponent } from './components/result-test/result-test.component';



const routes: Routes = [
  { path: '', redirectTo: '/test', pathMatch: 'full' },
  { path: 'test', component: TestComponent },
  { path: 'result', component: ResultTestComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }