
export enum Role {
    Admin,
    Etudiant
    }
  export class user {
      idUser: number;
      firstName: string;
      lastName: string;
      email: string;
      password: string;
      role: Role;
      locked: boolean = false;
      enabled: boolean = false;
    
      constructor(
        firstName: string,
        lastName: string,
        email: string,
        password: string,
        role: Role
      ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
      }
    }
    