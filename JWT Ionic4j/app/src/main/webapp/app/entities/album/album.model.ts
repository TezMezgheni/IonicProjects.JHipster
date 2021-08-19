import * as dayjs from 'dayjs';
import { IUser } from 'app/entities/user/user.model';

export interface IAlbum {
  id?: number;
  title?: string;
  description?: string | null;
  created?: dayjs.Dayjs | null;
  user?: IUser | null;
}

export class Album implements IAlbum {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string | null,
    public created?: dayjs.Dayjs | null,
    public user?: IUser | null
  ) {}
}

export function getAlbumIdentifier(album: IAlbum): number | undefined {
  return album.id;
}
