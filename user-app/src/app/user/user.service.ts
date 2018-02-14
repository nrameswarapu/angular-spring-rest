import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';
import { User } from './Users';

@Injectable()
export class UserService {

  private apiUrl = 'http://localhost:8080/Rest-Spring/users';

  constructor(private http: Http) { }

  findAll(): Observable<User[]> {
    return this.http.get(this.apiUrl)
      .map( (res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server Error'));
  }

  findById(id: number): Observable<User> {
    return null;
  }

  saveUser(user: User): Observable<User> {
    return null;
  }

  deleteById(id: number): Observable<boolean> {
    return null;
  }

  updateUser(user: User): Observable<User> {
    return null;
  }
}
