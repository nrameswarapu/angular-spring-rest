import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../Users';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  providers: [UserService]
})
export class UserListComponent implements OnInit {

  private users: User[];

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit() {
    this.getAllUsers();
  }

  redirectNewUserPage() {
    this.router.navigate(['/user/create']);
  }

  editUserPage(user: User) {
    if (user) {
      this.router.navigate(['user/edit', user.id]);
    }
  }
  deleteUser(user: User) {
    console.log('Delete User');
  }
  getAllUsers() {
    this.userService.findAll().subscribe(
      users => {
        this.users = users;
        console.log(this.users);
      },
      err => {
        console.log(err);
      }

    );
  }

}
