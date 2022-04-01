package meli.com.co.infrastructure.shared.dto;

public class ResponseDto {
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Shards get_shards() {
        return _shards;
    }

    public Integer getTotal() {
        return _shards.getTotal();
    }

    public void set_shards(Shards _shards) {
        this._shards = _shards;
    }

    String result;
    Shards _shards;
}

class Shards{
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    Integer total;
}
