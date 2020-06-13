export class Token {
    accessToken: string;
    tokenType: string;
    refreshToken: string;
    expiresIn: number;
    scope: string;
    jti: string;
    userId: number;

    constructor(accessToken: string, tokenType: string, refreshToken: string, expiresIn: number,
                scope: string, jti: string, userId: number) {
            this.accessToken = accessToken;
            this.tokenType = tokenType;
            this.refreshToken = refreshToken;
            this.expiresIn = expiresIn;
            this.scope = scope;
            this.jti = jti;
            this.userId = userId;
    }

}
