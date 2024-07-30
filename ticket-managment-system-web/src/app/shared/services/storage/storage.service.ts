import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class StorageService {

    public Save(key: string, value: string) {
        localStorage.setItem(key, value);
    }

    public Get(key: string): string | null {
        return localStorage.getItem(key);
    }

    public Remove(key: string) {
        localStorage.removeItem(key);
    }
    
    public Clear() {
        localStorage.clear();
    }
}