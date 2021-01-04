package com.teste.deliverIt.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="TB_CONTA")
public class Conta implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull(message = "Error: nome cannot be null")
	private String nome;
	@NotNull(message = "Error: valor cannot be null")
	private Double valorOriginal;
	@NotNull(message = "Error: dataVencimento cannot be null")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	@NotNull(message = "Error: dataPagamento cannot be null")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	private Integer diasAtraso;
	private Double valorCorrigido;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Double getValorOriginal() {
		return valorOriginal;
	}
	public void setValorOriginal(Double valorOriginal) {
		this.valorOriginal = valorOriginal;
	}
	public Double getValorCorrigido() {
		return valorCorrigido;
	}
	public void setValorCorrigido(Double valorCorrigido) {
		this.valorCorrigido = valorCorrigido;
	}
	public Integer getDiasAtraso() {
		return diasAtraso;
	}
	public void setDiasAtraso(Integer diasAtraso) {
		this.diasAtraso = diasAtraso;
	}
}